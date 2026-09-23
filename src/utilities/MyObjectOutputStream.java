/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.io.*;

/**
 *
 * @author ivanv
 */

public class MyObjectOutputStream extends ObjectOutputStream {

    protected void writeStreamHeader() throws IOException {
        reset();
    }

    //Constructores
    public MyObjectOutputStream() throws IOException {
        super();
    }

    public MyObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }
}
