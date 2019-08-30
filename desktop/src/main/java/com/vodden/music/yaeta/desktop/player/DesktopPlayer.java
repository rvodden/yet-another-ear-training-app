package com.vodden.music.yaeta.desktop.player;

import com.vodden.music.yaeta.player.Player;

import javax.inject.Inject;

public class DesktopPlayer implements Player {
  private org.jfugue.player.Player player;

  @Inject
  public DesktopPlayer(org.jfugue.player.Player player) {
    this.player = player;
  }

  @Override
  public void play(String play) {
    player.play(play);
  }
}
