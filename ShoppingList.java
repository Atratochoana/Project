import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ShoppingList {
    public static void main(String[] args) {

        out o = new out();
        o.printBorder();
        //addItem("apple", 2);
        //updateItem("test", 3);
        removeItem("apple");
        readItems();
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
        
        System.out.println("Items " + d.get("Items"));
    }

    public static void updateItem(String item, int NumsCollected) {
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

        System.out.println(d);
        return d;
    }
};

class out {
    int length = 20;
    int depth = 15;

    public void printBorder() {
        System.out.print("+");

        for (int i = 0; i < length - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
        for (int i = 0; i < depth -2; i++) {
            System.out.print("|");

            System.out.print(" " + (i+1) + ".");

            for (int x = 0; x < length-(5+((i+1)/10)); x++) {
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.print("+");
        for (int i = 0; i < length - 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
        
    }
}