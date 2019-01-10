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
		
		@Nullable
		public Type getAttachmentType(String name)
		{
			for(Type type : values())
			{
				if(type.getName().equals(name))
				{
					return type;
				}
			}
			
			return null;
		}
	}
}
