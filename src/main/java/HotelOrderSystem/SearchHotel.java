package HotelOrderSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SearchHotel {

	Scanner s = new Scanner(System.in);
	protected String Hotels= System.getProperty("user.dir")+"\\InputFile_Utils\\HotelMenus.xlsx";
	protected String GetHotelName ;
	protected Sheet sheet;
	protected Cell FoodItemCell;
	protected Cell Item_Price;

	protected String Food;
	protected double Price;
	protected boolean ishotelthere=false;
	
	protected int RowCount;
	protected Row row;
	
	public void FindHotel() throws IOException
	{
		
				File f = new File(Hotels);
				try (FileInputStream fis = new FileInputStream(f);
		                 XSSFWorkbook wbk = new XSSFWorkbook(fis))
				{
				
				System.out.println("ORDER YOUR FOOD HERE");
				System.out.println("Available Hotels: ");
				int Sno=1;
				
				for(int sheetcount = 0 ; sheetcount <wbk.getNumberOfSheets() ; sheetcount++)
				{
					String getHotelsList = wbk.getSheetName(sheetcount);
					if(getHotelsList.equalsIgnoreCase("Desert land"))
					{
						continue;
					}
					else
					{ 
						System.out.println(Sno+" "+getHotelsList);
					}
					Sno++;
				}//To Display list of hotels available...
			
				System.out.println("Enter Hotelname: ");
				GetHotelName = s.nextLine();
			
					for(int sheetcount = 0 ; sheetcount < wbk.getNumberOfSheets() ; sheetcount++)
					{
						sheet =  wbk.getSheetAt(sheetcount);
						if(sheet.getSheetName().equalsIgnoreCase(GetHotelName))
						{
							ishotelthere= true;
					
							System.out.println("\nWELCOME TO THE HOTEL "+sheet.getSheetName() );
							System.out.println("MENU\n----");
					
							RowCount = sheet.getPhysicalNumberOfRows();
					
							for(int i = 0; i<RowCount ; i++)
							{
								row = sheet.getRow(i);
						
								FoodItemCell = row.getCell(0);
								Item_Price = row.getCell(1);
						
								if(FoodItemCell.getCellType()==CellType.STRING && FoodItemCell.getCellType()!= null)
								{
									if(Item_Price.getCellType()==CellType.NUMERIC && Item_Price.getCellType()!= null)
									{
										Food = FoodItemCell.getStringCellValue();
										Price = Item_Price.getNumericCellValue();
								
									
										System.out.println(Food+" -₹" + Price);
									}
								}
								
							}
					
							break;
						}
					}// To Display Menu of Expected hotel...
			
					fis.close();
					wbk.close();
			}
		
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
		
		if(!ishotelthere)
		{
			System.out.println("Error: Hotel name not found!!");
		}//If Expected Hotel is not found...
	}
	
	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		SearchHotel sH = new SearchHotel();
		sH.FindHotel();

	}

}
