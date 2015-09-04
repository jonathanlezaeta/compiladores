package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;

public class main {
	  private Display d;
	  private Shell s;
	  
	  private Text codigoFuente;

	  main() {
	    d = new Display();
	    s = new Shell(d);
	    s.setMinimumSize(new Point(800, 600));
	    s.setSize(400, 400);
	    
	    s.setText("Compilador 2015");
	    //         create the menu system
	    Menu m = new Menu(s, SWT.BAR);
	    // create a file menu and add an exit item
	    final MenuItem file = new MenuItem(m, SWT.CASCADE);
	    file.setText("&File");
	    final Menu filemenu = new Menu(s, SWT.DROP_DOWN);
	    file.setMenu(filemenu);
	    final MenuItem openItem = new MenuItem(filemenu, SWT.PUSH);
	    openItem.setText("&Open\tCTRL+O");
	    openItem.setAccelerator(SWT.CTRL + 'O');
	    final MenuItem separator = new MenuItem(filemenu, SWT.SEPARATOR);
	    final MenuItem exitItem = new MenuItem(filemenu, SWT.PUSH);
	    exitItem.setText("E&xit");

	    class Open implements SelectionListener {
	      public void widgetSelected(SelectionEvent event) {
	        FileDialog fd = new FileDialog(s, SWT.OPEN);
	        fd.setText("Abrir");
	        fd.setFilterPath("C:/");
	        String[] filterExt = { "*.txt"};
	        fd.setFilterExtensions(filterExt);
	        String archivo = fd.open();
	        String linea;
	        String codigoCompleto = null;
	        try{
		        FileReader f = new FileReader(archivo);
		        BufferedReader b = new BufferedReader(f);
		        while((linea = b.readLine())!= null) {
		            codigoCompleto = codigoCompleto+linea+"\n";
		        }
		        b.close();
	        }
	        catch (Exception name) {
	        	System.out.println("Error.");
	        }
	        	codigoFuente.setText(codigoCompleto);
	    }

	    public void widgetDefaultSelected(SelectionEvent event) {
	      }
	    }

	    openItem.addSelectionListener(new Open());

	    exitItem.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        MessageBox messageBox = new MessageBox(s, SWT.ICON_QUESTION
	            | SWT.YES | SWT.NO);
	        messageBox.setMessage("Do you really want to exit?");
	        messageBox.setText("Exiting Application");
	        int response = messageBox.open();
	        if (response == SWT.YES)
	          System.exit(0);
	      }
	    });
	    s.setMenuBar(m);
	    
	    //Text area donde se carga el codigo fuente
	    codigoFuente = new Text(s,SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
	    codigoFuente.setBounds(10, 31, 764, 291);
	
	    Label lblCodigoFuente = new Label(s, SWT.NONE);
	    lblCodigoFuente.setBounds(10, 10, 100, 15);
	    lblCodigoFuente.setText("Codigo Fuente:");
	    //Boton para compilar
	    Button btnCompilar = new Button(s, SWT.NONE);
	    btnCompilar.setBounds(699, 506, 75, 25);
	    btnCompilar.setText("Compilar");
	    //Metodo que se ejecuta al precionar el boton
	    btnCompilar.addSelectionListener(new SelectionAdapter() {
	    	public void widgetSelected(SelectionEvent arg0) {
	    		System.out.println("Prueba");
	    	
	    	}
	    });
	    //Fin boton para compilar
	    
	    s.open();

	    while (!s.isDisposed()) {
	      if (!d.readAndDispatch())
	        d.sleep();
	    }
	    d.dispose();
	  }

	  public static void main(String[] argv) {
	    new main();
	  }
	}
