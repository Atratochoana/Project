import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ShoppingList {
    private static final out o = new out();

    public static void main(String[] args) {
        printCurrent();

        int selection = 0;
        do {

            o.printBorder();

            Scanner scanner = new Scanner(System.in);

            System.out.println("1. Add a new item to your shopping list");
            System.out.println("2. Update how many of one item you have collected");
            System.out.println("3. Remove an Item");
            System.out.println("4. Exit the program");
            int output = scanner.nextInt();
            scanner.nextLine();

            switch (output){
                case 1:
                    System.out.println("Enter your new Items name ");
                    String name = scanner.nextLine();
                    System.out.println("Enter how much of this product you want to collect ");
                    int count = scanner.nextInt();
                    scanner.nextLine();
                    addItem(name, count);
                    break;
                case 2:
                    System.out.println("Enter your Items name ");
                    name = scanner.nextLine();
                    System.out.println("Enter how much of this product have you collected ");
                    count = scanner.nextInt();
                    scanner.nextLine();
                    updateItem(name, count);
                    break;
                case 3:
                    System.out.println("Enter your Items name ");
                    name = scanner.nextLine();
                    scanner.nextLine();
                    removeItem(name);
                    break;
                case 4:
                    selection = 4;
                    break;
                default:
                    System.out.println("Invalid input try again.");
                    break;
                    
            }
            
        } while (selection != 4);
    }

    public static void addItem(String item, int count) {
        String fileName = "Items.json";

        JsonObject d;

        try (FileReader reader = new FileReader(fileName)) {
            d = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            d = new JsonObject();
            d.add("Test", new JsonArray());
}

        JsonArray items =  d.getAsJsonArray("Items");

        JsonObject newItem = new JsonObject();
        newItem.addProperty("Name", item);
        newItem.addProperty("NumToCollect", count);
        newItem.addProperty("NumsCollected", 0);

        items.add(newItem);

        try (FileWriter writer = new FileWriter("Items.json")) {
            writer.write(d.toString());
            writer.close();

        } catch (IOException e) {
            System.out.println("Failed");
        }
        
        o.addLine(item, 0, count);
        //System.out.println("Items " + d.get("Items"));
    }

    public static void updateItem(String item, int NumsCollected) {
        String fileName = "Items.json";

        JsonObject d;

        try (FileReader reader = new FileReader(fileName)) {
            d = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            d = new JsonObject();
            d.add(item, new JsonArray());
        }

        JsonArray items =  d.getAsJsonArray("Items");

        int size = (items.asList().size());

        for (int i = 0; i < size; i++) {
            JsonObject it = items.get(i).getAsJsonObject();
            if (it.get("Name").toString().equals('"' + item + '"')) {
                it.addProperty("NumsCollected", NumsCollected);
                System.out.println("Updated Nums Collected");
            }
        }

        try (FileWriter writer = new FileWriter("Items.json")) {
            writer.write(d.toString());
            writer.close();

        } catch (IOException e) {
            System.out.println("Failed");
        }

        System.out.println("Test " + d.toString());
        
    }

    public static void removeItem(String item) {
        String fileName = "Items.json";

        JsonObject d;

        try (FileReader reader = new FileReader(fileName)) {
            d = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            d = new JsonObject();
            d.add("Test", new JsonArray());
        }

        JsonArray items =  d.getAsJsonArray("Items");

        int size = (items.asList().size());

        for (int i = 0; i < size; i++) {
            JsonObject it = items.get(i).getAsJsonObject();
            if (it.get("Name").toString().equals('"' + item + '"')) {
                items.remove(i);
                System.out.println("Removed " + item);
            }
        }

        try (FileWriter writer = new FileWriter("Items.json")) {
            writer.write(d.toString());
            writer.close();

        } catch (IOException e) {
            System.out.println("Failed");
        }

    }

    public static JsonObject readItems() {
        String fileName = "Items.json";

        JsonObject d;

        try (FileReader reader = new FileReader(fileName)) {
            d = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            d = new JsonObject();
        }

        //System.out.println(d);
        return d;
    }

    public static void printCurrent() {
        JsonObject d = readItems();

        JsonArray items =  d.getAsJsonArray("Items");

        int size = (items.asList().size());

        for (int i = 0; i < size; i++) {
            JsonObject it = items.get(i).getAsJsonObject();
            
            o.addLine(it.get("Name").getAsString(), it.get("NumsCollected").getAsInt(), it.get("NumToCollect").getAsInt());
        }
    }
};

class out {
    int length = 10;
    int depth = 2;
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<Integer> collected = new ArrayList<Integer>();
    ArrayList<Integer> toCollect = new ArrayList<Integer>();

    public void printBorder() {
        System.out.print("+");

        for (int i = 0; i < length - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
        for (int i = 0; i < depth -2; i++) {
            System.out.print("|");

            System.out.print(" " + (i+1) + ". ");

            System.out.print(items.get(i));

            int collectedDigits = Integer.toString(collected.get(i)).length();
            int targetDigits = Integer.toString(toCollect.get(i)).length();
            int end = length - 11 - collectedDigits - targetDigits - items.get(i).length();

            for (int x = 0; x < Math.max(0, end); x++) {
                System.out.print(" ");
            }

            System.out.print(" [" + collected.get(i) + "/" + toCollect.get(i) + "] ");

            System.out.println("|");
        }
        System.out.print("+");
        for (int i = 0; i < length - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
        
    }

    public void addLine(String item, int NumCollected, int NumToCollect) {
        items.add(item);
        collected.add(NumCollected);
        toCollect.add(NumToCollect);
        int requiredLength = item.length() + 11 + Integer.toString(NumCollected).length() + Integer.toString(NumToCollect).length();
        length = Math.max(length, requiredLength);
        depth++;
    }
}