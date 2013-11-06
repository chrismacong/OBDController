package com.fix.obd.util.obdcharacter.decode;

import java.util.ArrayList;

import com.fix.obd.web.model.util.CharacterIterator;

public interface Decode {
	public void print(CharacterIterator cha);
	public String ReplyForOperation(CharacterIterator cha);
}
