package com.vodden.music.yaeta;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.vodden.music.yaeta.game.ScaleDegreeQuestion;

import javax.inject.Inject;

public class Application extends ApplicationAdapter {

  private ScaleDegreeQuestion question;

  private Stage stage;
  private Label label;

  @Inject
  public Application(ScaleDegreeQuestion question) {
    this.question = question;
  }

  @Override
  public void create() {
    AssetManager manager = new AssetManager();
    FileHandleResolver resolver = new InternalFileHandleResolver();

    manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
    manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
    manager.setLoader(Skin.class, ".json", new SkinLoader(resolver));

    manager.load("realbook-webfont.ttf", FreeTypeFontGenerator.class);

    manager.finishLoading();

    stage = new Stage(new ScreenViewport());

    FreeTypeFontGenerator fontGenerator = manager.get("realbook-webfont.ttf");
    FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    parameter.size = 48;
    parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + "\u0023\u044C";
    BitmapFont font = fontGenerator.generateFont(parameter);
    fontGenerator.dispose();

    LabelStyle labelStyle = new LabelStyle(font, Color.WHITE);

    label = new Label("No note", labelStyle);
    label.setSize(200, 50);
    label.setPosition(stage.getWidth() / 2 - 50, stage.getHeight() / 2);

    stage.addActor(label);

    Gdx.input.setInputProcessor(stage);

    new Thread(question, "Question").start();
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    try {
      label.setText(question.getDisplayText());
    } catch (NullPointerException npe) {
      label.setText("No note");
    }
    stage.act(Gdx.graphics.getDeltaTime());
    stage.draw();
  }

}
