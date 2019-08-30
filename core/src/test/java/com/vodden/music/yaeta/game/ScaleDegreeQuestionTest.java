package com.vodden.music.yaeta.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScaleDegreeQuestionTest {

  @Test()
  public void invalidScaleDegreeThrowsIllegalArgumentException() {
    Assertions.assertThrows(IllegalArgumentException.class,() -> {
      ScaleDegreeQuestion scaleDegreeQuestion = new ScaleDegreeQuestion(null,null, null);
      scaleDegreeQuestion.configure(null,13);
    });

    Assertions.assertThrows(IllegalArgumentException.class,() -> {
      ScaleDegreeQuestion scaleDegreeQuestion = new ScaleDegreeQuestion(null,null,null);
      scaleDegreeQuestion.configure(null, 0);
    });
  }

}
