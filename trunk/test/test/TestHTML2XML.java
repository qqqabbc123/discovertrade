package test;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.io.*;
import org.w3c.tidy.Tidy;

public class TestHTML2XML {
	private String url;
	private String outFileName;
	private String errOutFileName;

	public TestHTML2XML(String url, String outFileName, String errOutFileName) {
		this.url = url;
		this.outFileName = outFileName;
		this.errOutFileName = errOutFileName;
	}

	public void convert() {
		URL u = null;
		BufferedInputStream in;
		FileOutputStream out;

		Tidy tidy = new Tidy();

		// Tell Tidy to convert HTML to XML
		tidy.setXmlOut(true);

		try {
			// Set file for error messages
			tidy
					.setErrout(new PrintWriter(new FileWriter(errOutFileName),
							true));
			try {
				u = new URI(url).toURL();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Create input and output streams
			in = new BufferedInputStream(u.openStream());
			out = new FileOutputStream(outFileName);

			// Convert files
			tidy.parse(in, out);

			// Clean up
			in.close();
			out.close();

		} catch (IOException e) {
			System.out.println(this.toString() + e.toString());
		}
	}

	public static void main(String[] args) {
		/*
		 * Parameters are: URL of HTML file Filename of output file Filename of
		 * error file
		 */
		TestHTML2XML t = new TestHTML2XML("file:///media/disk/Users/KUSHAL/tutorial/tutorial/essential/io/file.html", 
				"/tmp/outt.txt", "/tmp/errr.txt");
		t.convert();
	}
}
