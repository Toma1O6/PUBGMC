package com.toma.pubgmc.util;

public interface IBuilder<RESULT>
{
	RESULT build();
	
	default void checkNotNull(Object obj) throws NullPointerException
	{
		if(obj == null)
			throw new NullPointerException(obj + " cannot be null!");
	}
	
	default void checkInt(int integer, int min, int max) throws IllegalArgumentException
	{
		if(integer > max || integer < min)
			throw new IllegalArgumentException(integer + " doesn't fit into the <" + min + ";" + max + "> range!");
	}
	
	default void checkFloat(float floatValue, float min, float max) throws IllegalArgumentException
	{
		if(floatValue > max || floatValue < min)
			throw new IllegalArgumentException(floatValue + " doesn't fit into the <" + min + ";" + max + "> range!");
	}
	
	default void checkDouble(double doubleValue, double min, double max) throws IllegalArgumentException
	{
		if(doubleValue > max || doubleValue < min)
			throw new IllegalArgumentException(doubleValue + " doesn't fit into the <" + min + ";" + max + "> range!");
	}
	
	default void checkBoolean(boolean bool, boolean expectedValue) throws IllegalArgumentException
	{
		if(bool != expectedValue)
			throw new IllegalArgumentException(bool + " is not equal the " + expectedValue);
	}
}
