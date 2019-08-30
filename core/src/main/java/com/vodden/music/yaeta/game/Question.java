package com.vodden.music.yaeta.game;

public abstract class Question implements Runnable {
  public abstract void run();

  public abstract void ask();
}
