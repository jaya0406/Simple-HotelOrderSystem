package HotelOrderSystem;

import java.io.IOException;

public class GenerateDiscount extends OrderService
{
	protected double discount;
	protected double TotalAmount;
	protected double discountrate;
	
	protected boolean IsAmountthere=false;
	
	public void DiscountRate() throws IOException
	{
	
		GetUSerOrder();
		
		if(super.Foodthere==true)
		{
			double SubTotal = super.SubTotal;
			
			if(SubTotal>150 && SubTotal<=300)
			{
				discountrate= 7;
				discount = SubTotal *(discountrate/100); 
				TotalAmount= SubTotal -discount;
				IsAmountthere=true;
			}
			
			if(SubTotal>300 && SubTotal<=500)
			{
				discountrate= 12;
				discount = SubTotal *(discountrate/100); 
				TotalAmount= SubTotal -discount;
				IsAmountthere=true;
			}
			

			if(SubTotal>500 && SubTotal<=1000)
			{
				discountrate= 15;
				discount = SubTotal *(discountrate/100); 
				TotalAmount= SubTotal -discount;
				IsAmountthere=true;
			}
			
			if(SubTotal>1000)
			{
				discountrate= 18;
				discount = SubTotal *(discountrate/100); 
				TotalAmount= SubTotal -discount;
				IsAmountthere=true;
			}
			else
			{
				discountrate= 5;
				discount = SubTotal *(discountrate/100); 
				TotalAmount= SubTotal -discount;
				IsAmountthere=true;
			}
		}
		
	}

	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		GenerateDiscount GD= new GenerateDiscount();
		GD.DiscountRate();
	}

}
