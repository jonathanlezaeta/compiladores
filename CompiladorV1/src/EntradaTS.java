package src;

public class EntradaTS {
	private Short id;
    private String lexema;
    private String tipo;
    private int contRef;
	
	
	public int getContRef() {
		return contRef;
	}

	public void setContRef(int contRef) {
		this.contRef = contRef;
	}

	public String getTipo() {
		return tipo;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public EntradaTS(Short id, String lexema) {
		this.id = id; 
		this.setLexema(lexema);
		tipo = null;
		contRef = 1;
	}

	public Short getId() {
		return this.id;
	}

	public void incrementarCont() {
		contRef++;
		
	}
	  public void decContRef(){
	        this.contRef--;
	    }

	public void setTipo(String tipo) {
		this.tipo = tipo;
		
	}

	public String getLexema() {
		return lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	 public void invertir(){
	        this.lexema= '-'+this.lexema;
	    }
	    public String imprimir(){
	        return lexema;
	    }

}
