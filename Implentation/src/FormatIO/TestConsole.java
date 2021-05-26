/*
 *	TestConsole.java
 *	==============
 *      Copyright (C) 1999, Ron Poet, Dept. Comp.Sci. Uni Glasgow Scotland
 *	$Author: ron $
 *	$Date: 1999/09/03 14:49:44 $
 *	$Revision: 1.5 $
 */

package	TestProg;

import	FormatIO.*;

/*========================*/
public	class	TestConsole
/*========================*/
	{

/*---------------------------------------*/
public	static	void	main(String[] arg)
/*---------------------------------------*/
	{
	Console	con = new Console(10, 10);

	con.println(true);
	con.println(true, 10);
	con.println(true, 10, '#');
	con.println(true, 10, '#', Format.RIGHT);
	con.println(true, 10, '#', Format.CENTER);

	con.println("Hello");
	con.println("Hello", 10);
	con.println("Hello", 10, '#');
	con.println("Hello", 10, '#', Format.RIGHT);
	con.println("Hello", 10, '#', Format.CENTER);

	con.println("How are you");
	con.println("How are you", 20);
	con.println("How are you", 20, '#');
	con.println("How are you", 20, '#', Format.RIGHT);
	con.println("How are you", 20, '#', Format.CENTER);

	con.println(3.14159);
	con.println(3.14159, 10);
	con.println(3.14159, 10, 2);
	con.println(3.14159, 10, 2, '#');
	con.println(3.14159, 10, 2, '#', Format.LEFT);
	con.println(3.14159, 10, 2, '#', Format.CENTER);

	con.println(42);
	con.println(42, 10);
	con.println(42, 10, '#');
	con.println(42, 10, '#', Format.LEFT);
	con.println(42, 10, '#', Format.CENTER);

	con.println('x');
	con.println('x', 10);
	con.println('x', 10, '#');
	con.println('x', 10, '#', Format.RIGHT);
	con.println('x', 10, '#', Format.CENTER);

	double	d = 0;
	int	i = 0;
	con.writeString("\nType a real: ");
	d = con.readDouble();
	con.writeString("d=");
	con.writeDouble(d);

	con.writeString("\nType a real: ");
	d = con.readDouble();
	con.writeString("d=");
	con.writeDouble(d);

	con.writeString("\nType an int: ");
	i = con.readInt();
	con.writeString("i=");
	con.writeInt(i);

	con.writeString("\nType an int: ");
	i = con.readInt();
	con.writeString("i=");
	con.writeInt(i);
	con.println();	// add a newline

		// test the get methods
	con.skipLine();	// gets rid of input newline after integer
	con.writeString("Type any 5 characters: ");
	char	c1 = con.getChar();
	con.writeChar(c1);
	char	c2 = con.getChar();
	con.writeChar(c2);
	char	c3 = con.getChar();
	con.writeChar(c3);
	char	c4 = con.getChar();
	con.writeChar(c4);
	char	c5 = con.getChar();
	con.writeChar(c5);
	con.println();

	con.writeString("Type any line: ");
	String	line = con.getLine();
	con.println(line);

	for (;;)
		{
		con.writeString("$ ");
		String	s = con.readLine();
		con.writeString("  [" + s + "]\n");
		}
        }

	}
