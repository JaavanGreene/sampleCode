package com.me.calculator;

import screens.LaunchScreen;

import com.badlogic.gdx.Game;

public class Calculator extends Game /* implements ApplicationListener */{
	@Override
	public void create() {
		setScreen(new LaunchScreen(this));
	}
}