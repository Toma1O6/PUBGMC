package com.toma.pubgmc.common.items.guns.attachments;

import javax.annotation.Nullable;

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
