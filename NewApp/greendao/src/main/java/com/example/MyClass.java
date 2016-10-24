package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {
    public static void main(String[] args) throws Exception {
//step 1:
        Schema schema = new Schema(100, "charles.nocompany.greendao");
//step 2:
        addTest(schema);
//step 3:
        new DaoGenerator().generateAll(schema,"./app/src/main/java-gen");
    }

    private static void addTest(Schema schema) {
        Entity testTable = schema.addEntity("TestTable1");
        testTable.addIdProperty().primaryKey().autoincrement();
        testTable.addStringProperty("text1").notNull();
        testTable.addStringProperty("comment1");
        testTable.addDateProperty("date1");
    }
}
