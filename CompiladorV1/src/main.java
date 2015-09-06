package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;

public class main implements Mensajes{
	  private Display d;
	  private Shell s;
	  private AnalizadorLexico analizarLexico;
	  private Text codigoFuente;
	  private Table table;
	  private Text text;
	  private Text text_1;
	  
	  main() {
	    d = new Display();
	    s = new Shell(d);
	    s.setMinimumSize(new Point(800, 600));
	    s.setSize(400, 400);
	    s.setText("Compilador 2015");
	    //Crea el menu para abrir el archivo
	    Menu m = new Menu(s, SWT.BAR);
	    // create a file menu and add an exit item
	    final MenuItem file = new MenuItem(m, SWT.CASCADE);
	    file.setText("&Archivo");
	    final Menu filemenu = new Menu(s, SWT.DROP_DOWN);
	    file.setMenu(filemenu);
	    final MenuItem openItem = new MenuItem(filemenu, SWT.PUSH);
	    openItem.setText("&Abrir\tCTRL+O");
	    openItem.setAccelerator(SWT.CTRL + 'O');
	    final MenuItem separator = new MenuItem(filemenu, SWT.SEPARATOR);
	    final MenuItem exitItem = new MenuItem(filemenu, SWT.PUSH);
	    exitItem.setText("Salir");
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
	    //Boton para compilar
	    Button btnCompilar = new Button(s, SWT.NONE);
	    btnCompilar.setBounds(699, 506, 75, 25);
	    btnCompilar.setText("Compilar");
	    
	    TabFolder tabFolder = new TabFolder(s, SWT.NONE);
	    tabFolder.setBounds(10, 10, 764, 478);
	    
	    TabItem tbtmCodigoFuente = new TabItem(tabFolder, SWT.NONE);
	    tbtmCodigoFuente.setText("Codigo Fuente");
	    
	    //Text area donde se carga el codigo fuente
	    codigoFuente = new Text(tabFolder,SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
	    tbtmCodigoFuente.setControl(codigoFuente);
	    
	    TabItem tbtmTablaDeSimbolos = new TabItem(tabFolder, SWT.NONE);
	    tbtmTablaDeSimbolos.setText("Tabla de Simbolos");
	    
	    table = new Table(tabFolder, SWT.FULL_SELECTION);
	    table.setBackground(SWTResourceManager.getColor(211, 211, 211));
	    table.setHeaderVisible(true);
	    table.setLinesVisible(true);
	    tbtmTablaDeSimbolos.setControl(table);
	    
	    TableColumn tblclmnTipoDeToken = new TableColumn(table, SWT.NONE);
	    tblclmnTipoDeToken.setWidth(252);
	    tblclmnTipoDeToken.setText("Tipo de Token");
	    
	    TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn.setMoveable(true);
	    tblclmnNewColumn.setWidth(270);
	    tblclmnNewColumn.setText("Nombre Original");
	    
	    TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
	    tblclmnNewColumn_1.setMoveable(true);
	    tblclmnNewColumn_1.setWidth(232);
	    tblclmnNewColumn_1.setText("Identificador");

	    
	    TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
	    tbtmNewItem.setText("Errores");
	    
	    text = new Text(tabFolder, SWT.BORDER);
	    text.setBackground(SWTResourceManager.getColor(255, 0, 0));
	    tbtmNewItem.setControl(text);
	    
	    TabItem tbtmWarning = new TabItem(tabFolder, SWT.NONE);
	    tbtmWarning.setText("Warnings");
	    
	    text_1 = new Text(tabFolder, SWT.BORDER);
	    text_1.setBackground(SWTResourceManager.getColor(255, 255, 0));
	    tbtmWarning.setControl(text_1);
	    
	    TabItem tbtmTokens = new TabItem(tabFolder, SWT.NONE);
	    tbtmTokens.setText("Tokens");
	    
	    //Metodo que se ejecuta al precionar el boton
	    btnCompilar.addSelectionListener(new SelectionAdapter() {
	    	public void widgetSelected(SelectionEvent arg0) {
	    		//Funcion encargada de analizar el codigo fuente
	    		analizar(codigoFuente.getText());
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
	  
  
	//------------------------------------------------------------Fin interfaz grafica------------------------------------------------------------	  
	  
	  protected void analizar(String codigoFuente) {
		  AnalizadorLexico anlLexico = new AnalizadorLexico(codigoFuente, this);
		  System.out.println(codigoFuente);
		  for (int i = 0; i < 50; i++) {
			  System.out.println(anlLexico.yylex());
		  }
	  }
	  
	//----------------------------------------------------------Inicializa el compilador----------------------------------------------------------
	public static void main(String[] argv) {
		new main();
	}
	  
	//-----------------------------------------------Implementacion metodos de la interfaz Mensaje------------------------------------------------
	public void tablaDeSimbolos() {
		// TODO Auto-generated method stub
		
	}

	public void error(int nroLinea, String mensaje, String string) {
		// TODO Auto-generated method stub
		
	}

	public void token(int nroLinea, String lexema) {
		// TODO Auto-generated method stub
		
	}

	public void warning(String string) {
		// TODO Auto-generated method stub
		
	}

	public void estructuraSintactica(int linea, String estructura) {
		// TODO Auto-generated method stub
		
	}  
}
