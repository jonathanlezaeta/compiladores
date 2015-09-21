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
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import java.util.Enumeration;
import java.util.Hashtable;

import org.eclipse.swt.custom.Bullet;
import org.eclipse.swt.custom.ST;
import org.eclipse.swt.custom.StyledText;

public class main implements Mensajes {
	private Display d;
	private Shell s;
	private final StyledText codigoFuente;
	private Table table;
	private Text erroresText;
	private Text warningText;
	private AnalizadorLexico anlLexico;
	private String erroresParaMostrar;
	private String warningParaMostrar;
	private String tokenAmostrar;
	private Text tokenText;
	
	main() {
		this.anlLexico = new AnalizadorLexico("", this);
		this.erroresParaMostrar = "";
		this.warningParaMostrar = "";
		this.tokenAmostrar = "";
		d = new Display();
		s = new Shell(d);
		s.setMinimumSize(new Point(800, 600));
		s.setSize(400, 400);
		s.setText("Compilador 2015");
		// Crea el menu para abrir el archivo
		Menu m = new Menu(s, SWT.BAR);
		// create a file menu and add an exit item
		final MenuItem file = new MenuItem(m, SWT.CASCADE);
		file.setText("&Archivo");
		final Menu filemenu = new Menu(s, SWT.DROP_DOWN);
		file.setMenu(filemenu);
		final MenuItem openItem = new MenuItem(filemenu, SWT.PUSH);
		openItem.setText("&Abrir\tCTRL+O");
		openItem.setAccelerator(SWT.CTRL + 'O');
		final MenuItem exitItem = new MenuItem(filemenu, SWT.PUSH);
		exitItem.setText("Salir");
		class Open implements SelectionListener {
			public void widgetSelected(SelectionEvent event) {
				FileDialog fd = new FileDialog(s, SWT.OPEN);
				fd.setText("Abrir");
				fd.setFilterPath("C:/");
				String[] filterExt = { "*.txt" };
				fd.setFilterExtensions(filterExt);
				String archivo = fd.open();
				String linea;
				String codigoCompleto = "";
				try {
					FileReader f = new FileReader(archivo);
					BufferedReader b = new BufferedReader(f);
					while ((linea = b.readLine()) != null) {
						codigoCompleto = codigoCompleto + linea + "\n";
					}
					b.close();
				} catch (Exception name) {
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
		// Boton para compilar
		Button btnCompilar = new Button(s, SWT.NONE);
		btnCompilar.setBounds(699, 506, 75, 25);
		btnCompilar.setText("Compilar");
		/*
		 * Comienzan la interfaz grafica de las subpestañas y el al seleccionar
		 * una u otra
		 */
		final TabFolder tabFolder = new TabFolder(s, SWT.NONE);
		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (tabFolder.getSelectionIndex() == 1) {
					table.removeAll();
					tablaDeSimbolos();
				}
				if (tabFolder.getSelectionIndex() == 2) {
					erroresText.setText("");
					erroresText.setText(erroresParaMostrar);
				}
				if (tabFolder.getSelectionIndex() == 3) {
					warningText.setText("");
					warningText.setText(warningParaMostrar);
				}
				if (tabFolder.getSelectionIndex() == 4){
					tokenText.setText("");
					tokenText.setText(tokenAmostrar);
				}
				
			}
		});
		tabFolder.setBounds(10, 10, 764, 478);

		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("Codigo Fuente");

		codigoFuente = new StyledText(tabFolder, SWT.BORDER);
		tabItem.setControl(codigoFuente);

		codigoFuente.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				int maxLine = codigoFuente.getLineCount();
				int lineCountWidth = Math.max(String.valueOf(maxLine).length(),
						3);

				StyleRange style = new StyleRange();
				style.metrics = new GlyphMetrics(0, 0, lineCountWidth * 8 + 5);
				Bullet bullet = new Bullet(ST.BULLET_NUMBER, style);
				codigoFuente.setLineBullet(0, codigoFuente.getLineCount(), null);
				codigoFuente.setLineBullet(0, codigoFuente.getLineCount(),
						bullet);
			}
		});

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

		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Errores");

		erroresText = new Text(tabFolder, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.MULTI);
		tbtmNewItem.setControl(erroresText);
		erroresText.setBackground(SWTResourceManager.getColor(255, 0, 0));

		TabItem tbtmWarning = new TabItem(tabFolder, SWT.NONE);
		tbtmWarning.setText("Warnings");

		warningText = new Text(tabFolder, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.MULTI);
		warningText.setBackground(SWTResourceManager.getColor(255, 255, 0));
		tbtmWarning.setControl(warningText);

		TabItem tbtmTokens = new TabItem(tabFolder, SWT.NONE);
		tbtmTokens.setText("Tokens");
		
		tokenText = new Text(tabFolder, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		tbtmTokens.setControl(tokenText);

		// Metodo que se ejecuta al precionar el boton
		btnCompilar.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				// Funcion encargada de analizar el codigo fuente
				analizar(codigoFuente.getText());
			}
		});
		// Fin boton para compilar

		s.open();

		while (!s.isDisposed()) {
			if (!d.readAndDispatch())
				d.sleep();
		}
		d.dispose();
	}

	// ------------------------------------------------------------Fin interfaz
	// grafica------------------------------------------------------------

	protected void analizar(String codigoFuente) {
		anlLexico.setCodigoFuente(codigoFuente);
		System.out.println(codigoFuente);
//		for (int i = 0; i < codigoFuente.length(); i++) {
//			System.out.println(anlLexico.yylex());
//		}
		Parser analizadorSintactico = new Parser();
		analizadorSintactico.setLexico(anlLexico);
		analizadorSintactico.setMensajes(this);
		analizadorSintactico.run();
	}

	// ----------------------------------------------------------Inicializa el
	// compilador----------------------------------------------------------
	public static void main(String[] argv) {
		new main();
	}

	// -----------------------------------------------Implementacion metodos de
	// la interfaz Mensaje------------------------------------------------
	public void tablaDeSimbolos() {
		Hashtable<String, EntradaTS> aux = this.anlLexico.getTablaDeSimbolos()
				.getTabla();
		Enumeration<EntradaTS> e = aux.elements();
		while (e.hasMoreElements()) {
			EntradaTS entrada = e.nextElement();
			for (int i = 0; i < entrada.getContRef(); i++) {
				TableItem item = new TableItem(table, SWT.NULL);
				item.setText(0, entrada.getLexema());
				item.setText(1, this.getName(entrada.getId()));
			}
		}
	}

	private String getName(Short id) {
		switch (id) {
		case 264:
			return "Identificador";
		case 265:
			return "Constante";
		case 267:
			return "Cadena de caracteres";
		case 271:
			return "Constante Entera";
		default:
			return null;
		}
	}

	public void token(int nroLinea, String lexema) { // muestra los token a medida que se reconocen 
		tokenAmostrar = tokenAmostrar + "Línea " + nroLinea + ": " + lexema + "\n";
	}

	public void warning(String warning) { // muestra los warning
		// textoWarning.append(warning + "\n");
		warningParaMostrar = warningParaMostrar + warning + "\n";
	}

	public void estructuraSintactica(int linea, String estructura) { // Muestra
																		// la
																		// estructura
																		// sintactica
		// textoEstrSin.append("Línea " + linea + ": " + estructura + "\n");

	}

	public void error(int nroLinea, String error, String tipo) { // muestra los
																	// errores a
																	// medida
																	// que se
																	// reconocen
		erroresParaMostrar = erroresParaMostrar + "Línea " + nroLinea + ": " + error
				+ " - Tipo: " + tipo + "\n";
	}
}
