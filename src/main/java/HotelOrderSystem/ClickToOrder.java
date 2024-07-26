package HotelOrderSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class OrderService extends SearchHotel {

    protected String getUserItem;
    protected int getUserQuantity;
    protected double Rate;
    protected boolean Foodthere = false;
    protected double SubTotal;
    protected String ItemName;
    protected double Amount;

    protected ArrayList<String> UserItem = new ArrayList<>();
    protected ArrayList<Integer> Qty = new ArrayList<>();
    protected ArrayList<Double> GetPrice = new ArrayList<>();

    Scanner s = new Scanner(System.in);

    public void GetUSerOrder() throws IOException {
        try {
            FindHotel();

            if (super.ishotelthere)
            {
                System.out.println("\nPlace your Order!!");

                while (true)
                {
                    System.out.println("Enter Item Or 'Stop' to finish");
                    getUserItem = s.nextLine();

                    if (getUserItem.equalsIgnoreCase("stop"))
                    {
                        handleDesserts();//desert
                        break;
                    }

                    for (int i = 0; i < super.RowCount; i++)
                    {
                        super.row = sheet.getRow(i);
                        super.FoodItemCell = row.getCell(0);
                        super.Item_Price = row.getCell(1);

                        if (super.FoodItemCell != null && super.FoodItemCell.getCellType() == CellType.STRING) 
                        {
                            super.Food = FoodItemCell.getStringCellValue();
                            if (super.Food.equalsIgnoreCase(getUserItem))
                            {
                                Foodthere = true;

                                UserItem.add(getUserItem);

                                while (true)
                                {
                                    System.out.println("Enter Quantity ");
                                    try
                                    {
                                        getUserQuantity = Integer.parseInt(s.nextLine());
                                        break;
                                    } 
                                    catch (NumberFormatException e) 
                                    {
                                        System.out.println("Error: Invalid Entry. Please Enter Valid Number as Quantity.");
                                    }
                                }
                                Qty.add(getUserQuantity);

                                if (super.Item_Price != null && super.Item_Price.getCellType() == CellType.NUMERIC)
                                {
                                    super.Price = super.Item_Price.getNumericCellValue();
                                    GetPrice.add(super.Price);
                                }
                            }
                        }
                    }

                    if (!Foodthere) 
                    {
                        System.out.println("Error: Item Not Found!!!");
                    }
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.print("Error: " + e);
        }
    }

    private void handleDesserts() throws IOException 
    {
        System.out.println("Would you like to have some Dessert!! (YES/NO)");
        String userChoice = s.nextLine();
        if (userChoice.equalsIgnoreCase("yes")) 
        {
            System.out.println("Menu: " + "\n-----");
            File f1 = new File(super.Hotels);
            try (FileInputStream fis1 = new FileInputStream(f1);
                 XSSFWorkbook wbk1 = new XSSFWorkbook(fis1))
            {

                Sheet sheet1 = wbk1.getSheet("Desert Land");

                int RowcountofSheet = sheet1.getPhysicalNumberOfRows();

                for (int i = 0; i < RowcountofSheet; i++) {
                    Row row = sheet1.getRow(i);

                    Cell Item = row.getCell(0);
                    Cell Price = row.getCell(1);

                    if (Item != null && Item.getCellType() == CellType.STRING) {
                        if (Price != null && Price.getCellType() == CellType.NUMERIC) {
                            ItemName = Item.getStringCellValue();
                            Amount = Price.getNumericCellValue();

                            System.out.println(ItemName + " -₹" + Amount); // to display desert menu
                        }
                    }
                }

                while (true) 
                {
                    System.out.println("Enter Dessert Item Or 'Stop' to finish");
                    String getDesert = s.nextLine();

                    if (getDesert.equalsIgnoreCase("stop"))
                    {
                        break;
                    }

                    boolean dessertFound = false;
                    for (int i = 0; i < RowcountofSheet; i++) {
                        Row row = sheet1.getRow(i);
                        Cell Item = row.getCell(0);

                        if (Item != null && Item.getCellType() == CellType.STRING) 
                        {
                            ItemName = Item.getStringCellValue();
                            if (getDesert.equalsIgnoreCase(ItemName)) 
                            {
                                dessertFound = true;

                                Cell Price = row.getCell(1);
                                Amount = Price.getNumericCellValue();

                                UserItem.add(getDesert);

                                System.out.println("Enter Quantity: ");
                                int getDesertQty = s.nextInt();
                                s.nextLine(); // consume newline
                                Qty.add(getDesertQty);

                                GetPrice.add(Amount);
                                break;
                            }
                        }
                    }
                    if (!dessertFound)
                    {
                        System.out.println("Error: Dessert Item Not Found!!!");
                    }
                }
            }
        }
        displayOrder();
    }

	private void displayOrder() 
	{
        System.out.println("Your Order:-");
        for (int j = 0; j < UserItem.size(); j++) 
        {
            if (UserItem.get(j).length() < 6) 
            {
                System.out.println((j + 1) + ". " + UserItem.get(j) + "\t\t" + Qty.get(j) + " *₹" + GetPrice.get(j));
            }
            else
            {
                System.out.println((j + 1) + ". " + UserItem.get(j) + "\t" + Qty.get(j) + " *₹" + GetPrice.get(j));
            }
            Rate = GetPrice.get(j) * Qty.get(j);
            SubTotal += Rate;
        }
    }

	public ArrayList<String> getOrderedItems()
	{
        return UserItem;
	}

	public ArrayList<Integer> getOrderedQuantities()
	{
        return Qty;
	}

	public ArrayList<Double> getOrderedCosts()
	{
        return GetPrice;
	}

	public static void main(String[] args) throws IOException 
	{
        OrderService os = new OrderService();
        os.GetUSerOrder();
	}
}
