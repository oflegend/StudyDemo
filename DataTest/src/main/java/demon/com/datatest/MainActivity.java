package demon.com.datatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    String json1 = "{\"id\":1,\"name\":\"Java\"}";
    String json2 = "[\n" +
            "\t{\"id\":1,\"name\":\"Java\"},\n" +
            "\t{\"id\":2,\"name\":\"xml\"},\n" +
            "\t{\"id\":3,\"name\":\"Android\"}\n" +
            "]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        parseJSonObject(json1);
        parseJsonArray(json2);
        //parseGsonObject(json1);

       //parseGsonArray(json2);
    }

    private void parseJSonObject(String json1) {
        try {
            JSONObject object = new JSONObject(json1);
            int id = object.getInt("id");
            String name = object.getString("name");
            System.out.println(id + ":" + name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void parseJsonArray(String json) {
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int id = object.getInt("id");
                String name = object.getString("name");
                System.out.println(id + ":" + name);
                tv1.append(id + ":" + name + "\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseGsonObject(String json1) {
        Gson gson = new Gson();
        Book book = gson.fromJson(json1, Book.class);
        System.out.println(book.getId() + ":" + book.getName());
    }

    private void parseGsonArray(String json2) {
        Gson gson = new Gson();
        List<Book> booklist = gson.fromJson(json2, new TypeToken<List<Book>>() {
        }.getType());
        for (Book book : booklist) {
            System.out.println(book.getId() + ":" + book.getName());
        }
        String json = gson.toJson(booklist);
        System.out.println(json);
    }
}
