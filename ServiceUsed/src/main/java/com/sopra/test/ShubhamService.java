package com.sopra.test;

import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.WebProxy;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
//import microsoft.exchange.webservices.data.core.exception.service.remote.ServiceResponseException;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.AttachmentCollection;
import microsoft.exchange.webservices.data.property.complex.FileAttachment;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;

public class ShubhamService {
	 public static void main(String[] args) {
		 
		  TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				 try {
					 ExchangeService service=null;
					 service = new ExchangeService(ExchangeVersion.Exchange2007_SP1);
					   
					   ExchangeCredentials credentials = new WebCredentials("shubham.anurag@soprasteria.com", "Anu@shub1234*@");
				        service.setCredentials(credentials);
				      WebProxy proxy= new WebProxy("10.224.3.69", 8080);
			          service.setWebProxy(proxy);
				      
			           service.setUrl(new URI("https://outlook.office365.com/EWS/Exchange.asmx"));
				        //service.setUrl(new URI("https://in.mailbox.corp.sopra/EWS/Exchange.asmx"));
			            ItemView iview = new ItemView(10);
			            
			           
			            FindItemsResults findResults = service.findItems(WellKnownFolderName.Inbox, iview);

			            for (Object item :findResults)
			            {
			            	 Item itm = Item.bind(service, ((Item)item).getId(), PropertySet.FirstClassProperties);
			                 EmailMessage emailMessage = EmailMessage.bind(service, itm.getId());
			                 System.out.println(emailMessage.getSubject().toString());
			                 if(itm.getHasAttachments()){
		                	 AttachmentCollection attachmentCollection = itm.getAttachments();
			                 System.out.println(emailMessage.getAttachments());

		                	 for(int i=0;i<attachmentCollection.getCount();i++)
		                	 {
		                		 FileAttachment attachment = (FileAttachment)attachmentCollection.getPropertyAtIndex(i);
		                		 attachment.load("C:\\SaveAttachment\\"+attachment.getName());
		                	   }
		                 }
			            } 
			              service.close();
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
				
			}
		};
			Timer timer = new Timer();  
			long delay = 0;
			long intervalPeriod = 1*500000;
			timer.scheduleAtFixedRate(task, delay, intervalPeriod);
	 
         }

}
