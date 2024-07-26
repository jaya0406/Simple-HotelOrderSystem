package HotelOrderSystem;

import java.io.IOException;

public class Invoice extends GenerateDiscount
{
	public void getInvoice() throws IOException
	{
		DiscountRate();
		if(IsAmountthere==true)
		{	
				System.out.println("\nReceipt No. XXX");
				System.out.println("\n\t    Hotel "+super.sheet.getSheetName()+"\n----------------------------------------"+"\nItem\t\tQty  Rate\tAmount"+"\n----------------------------------------");
				
				for(int j=0;j<UserItem.size();j++)
				{
					if((UserItem.get(j).length())<=7)
					{
						System.out.println(UserItem.get(j).toUpperCase()+"\t\t  "+Qty.get(j)+"  * "+GetPrice.get(j)+"\t₹"+((Qty.get(j))*(GetPrice.get(j))));
					}
					
					if((UserItem.get(j).length())>7 && (UserItem.get(j).length())<=15)
					{
						System.out.println(UserItem.get(j).toUpperCase()+"\t  "+Qty.get(j)+"  * "+GetPrice.get(j)+"\t₹"+((Qty.get(j))*(GetPrice.get(j))));
					}
					
					if((UserItem.get(j).length())>15)
					{
						System.out.println(UserItem.get(j).toUpperCase()+" "+Qty.get(j)+"  * "+GetPrice.get(j)+"\t₹"+((Qty.get(j))*(GetPrice.get(j))));
					}
					
				}
				
				System.out.println("\t\t\t\t-------");
				System.out.println("Subtotal\t\t\t₹" + super.SubTotal);
				 System.out.println("Discount(" + (super.discountrate / 100) + ")\t\t\t-" + String.format("%.2f", discount));
				System.out.println("\t\t\t\t-------");
				System.out.println("Total Amount\t\t\t₹"+TotalAmount);
				System.out.println("----------------------------------------");
				
				System.out.println("      Thank You !! VISIT AGAIN 😊😊 ");
		}
		
		if(IsAmountthere==false)
		{
			return;
		}
	}
	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		Invoice i = new Invoice();
		i.getInvoice();
	}

}
