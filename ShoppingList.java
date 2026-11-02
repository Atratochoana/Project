import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ShoppingList {
    public static void main(String[] args) {

        out o = new out();
        o.printBorder();
        addItem("item", 5);
        readItems();
    }

    public static void addItem(String item, int count) {
        String fileName = "Items.json";

        JsonObject d;

        try (FileReader reader = new FileReader(fileName)) {
            d = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            d = new JsonObject();
            d.add("Items", new JsonArray());
}

        JsonArray items =  d.getAsJsonArray("Items");

        JsonObject newItem = new JsonObject();
        newItem.addProperty("Name", item);
        newItem.addProperty("NumToCollect", count);
        newItem.addProperty("NumsCollected", 0);

        items.add(newItem);
    }

    public static void updateItem(String item, int NumsCollected) {
        String filename = "Items.json";

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

    // public void addLine() {
    // }
}



        // try {
        //     // Read the existing JSON file
        //     JsonObject data;

        //     try (FileReader reader = new FileReader(fileName)) {
        //         data = JsonParser.parseReader(reader)
        //                          .getAsJsonObject();
        //     }

        //     // Get the array of shopping items
        //     JsonArray items = data.getAsJsonArray("Items");

        //     // Add a new item
        //     items.add("Eggs");

        //     // Write the updated JSON back to the file
        //     Gson gson = new GsonBuilder()
        //             .setPrettyPrinting()
        //             .create();

        //     try (FileWriter writer = new FileWriter(fileName)) {
        //         gson.toJson(data, writer);
        //     }

        //     System.out.println("Shopping list updated!");

        // } catch (IOException e) {
        //     System.out.println("Error accessing shopping.json: "
        //             + e.getMessage());
        // }