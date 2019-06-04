package com.toma.pubgmc.common.items.guns.attachments;

public interface IAttachment
{
	Type getType();
	
	enum Type
	{
		BARREL, GRIP, MAGAZINE, STOCK, SCOPE;
		
		public String getName()
		{
			return name().toLowerCase();
		}
	}
}
