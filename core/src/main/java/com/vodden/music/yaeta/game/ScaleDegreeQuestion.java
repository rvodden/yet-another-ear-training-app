package com.vodden.music.yaeta.game;

import com.vodden.music.yaeta.player.Player;
import sun.plugin.dom.exception.InvalidStateException;

import javax.inject.Inject;
import java.util.concurrent.ThreadLocalRandom;

public class ScaleDegreeQuestion extends Question {

  private Player player;
  private com.vodden.music.yaeta.tuner.Tuner tuner;
  private com.vodden.music.yaeta.tuner.PitchDetectionHandle pitchDetectionHandle;

  private com.vodden.music.yaeta.tuner.key.Key key;
  private Integer degree;

  @Inject
  public ScaleDegreeQuestion(Player player, com.vodden.music.yaeta.tuner.Tuner tuner, com.vodden.music.yaeta.tuner.PitchDetectionHandle pitchDetectionHandle) {
    this.player = player;
    this.tuner = tuner;
    this.pitchDetectionHandle = pitchDetectionHandle;
  }

  public void configure(com.vodden.music.yaeta.tuner.key.Key key, Integer degree) {
    setKey(key);
    setDegree(degree);
  }

  @Override
  public void run() {
    ask();
  }

  @Override
  public void ask() {
    player.play("D3+D5min7H G3+G4dom7^^H C3+C5maj7W");
    Integer randomNumber = ThreadLocalRandom.current().nextInt(1,5);
    switch (randomNumber) {
      case 1:
        player.play("Cw");
        break;
      case 2:
        player.play("Dw");
        break;
      case 3:
        player.play("Ew");
        break;
      case 4:
        player.play("Fw");
        break;
      default:
        throw new InvalidStateException("Generated random integer higher than 4");
    }

    tuner.initTuner();
  }

  public com.vodden.music.yaeta.tuner.key.Key getKey() {
    return key;
  }

  private void setKey(com.vodden.music.yaeta.tuner.key.Key key) {
    this.key = key;
  }

  public Integer getDegree() {
    return degree;
  }

  private void setDegree(Integer degree) {
    if (degree > 12 || degree < 1) {
      throw new IllegalArgumentException(String.format("Degree must be between 1 and 12, %d provided.\n",degree));
    }
    this.degree = degree;
  }


  public String getDisplayText() {
    return pitchDetectionHandle.getNote().toString();
  }

}
