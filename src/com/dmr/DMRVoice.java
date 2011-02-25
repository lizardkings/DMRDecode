package com.dmr;

import java.awt.Color;
import java.awt.Font;

public class DMRVoice {
	private String line[]=new String[10];
	private boolean res;
	private Font fonts[]=new Font[10];
	private Color colours[]=new Color[10];
	
	public String[] decode (DMRDecode theApp,byte[] dibit_buf)	{
		String cline;
		DecodeCACH cachdecode=new DecodeCACH();
		line[0]=theApp.getTimeStamp()+" DMR Voice Frame";
		fonts[0]=theApp.boldFont;
		colours[0]=Color.BLACK;
		// CACH decode
		cline=cachdecode.decode(theApp,dibit_buf);
		res=cachdecode.isPassErrorCheck();
		if (res==true)	{
			line[1]=cline;
			// If short LC data is available then display it
			if (cachdecode.getShortLC()==true)	{
				line[7]=cachdecode.getShortLCline();
				fonts[7]=theApp.boldFont;
				if (cachdecode.getshortLCError()==true) colours[7]=Color.RED;
				else colours[7]=Color.BLACK;
				cachdecode.clearShortLC();
			}
		}
		theApp.frameCount++;
		return line;
	}

	public boolean isError() {
		return res;
	}
	
	// Return the fonts in use
	public Font[] getFonts()	{
		return fonts;
	}
	
	// Return the colours in use
	public Color[] getColours()	{
		return colours;
	}

}
