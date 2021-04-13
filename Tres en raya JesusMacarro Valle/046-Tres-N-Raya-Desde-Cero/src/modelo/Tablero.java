package modelo;

import utiles.RespuestaColocacion;

public class Tablero {

	private int dimension = 3;
	private Tipo matriz[][] = new Tipo[dimension][dimension];
	private String errorActual = "";

	public Tipo getValorCasilla(int x, int y) {
		return matriz[x][y];
	}

	public Tablero() {
		super();
		inicializarMatriz();
	}

	public void inicializarMatriz() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				matriz[i][j] = Tipo.blanco;
			}
		}
	}

	public boolean limpiarCasilla(Coordenada coordenada) {
		if (getPosicion(coordenada) != Tipo.blanco) {
			setPosicion(coordenada, Tipo.blanco);
			return true;
		}
		return false;
	}

	private void setPosicion(Coordenada coordenada, Tipo blanco) {
		matriz[coordenada.getX()][coordenada.getY()] = blanco;

	}

	public boolean borrarCasilla(Coordenada coordenada, Tipo tipo) {
		// primero hay que borrar una casilla no bloqueada de tu turno
		if (isPropiedad(coordenada, tipo) && !comprobarCasillaBloqueada(coordenada)) {
			limpiarCasilla(coordenada);
			return true;
		}
		return false;
	}

	public boolean colocarFicha(Coordenada coordenada, Tipo tipoActual) {

		if (matriz[coordenada.getX()][coordenada.getY()] == Tipo.blanco) {
			matriz[coordenada.getX()][coordenada.getY()] = tipoActual;
//			System.out.println("coordenada puesta");
			return true;
		}
		return false;
	}

	public Tipo getPosicion(Coordenada coordenada) {
		return matriz[coordenada.getX()][coordenada.getY()];
	}

	private boolean isLibre(Coordenada coordenada) {
		return getPosicion(coordenada) == Tipo.blanco;
	}

	/**
	 * 
	 * @param coordenada
	 * @return false si encuentra una libre y true en caso contrario
	 */
	public boolean comprobarCasillaBloqueada(Coordenada coordenada) {
		for (int x = coordenada.getX() - 1; x <= coordenada.getX() + 1; x++)
			for (int y = coordenada.getY() - 1; y <= coordenada.getY() + 1; y++)
				if (x >= 0 && x < this.dimension && y >= 0 && y < this.dimension)
					if (this.isLibre(new Coordenada(x, y)))
						return false;
		return true;
	}

	public String getErrorActual() {
		return this.errorActual;
	}

	public boolean isPropiedad(Coordenada coordenada, Tipo tipo) {
		return getPosicion(coordenada) == tipo;
	}

	public boolean CompruebaFila() {
		for (int x = 0; x < 3; x++)
			if (matriz[x][0] == matriz[x][1] && matriz[x][1] == matriz[x][2] && matriz[x][1] != Tipo.blanco) {
				return true;
			}
		return false;
	}
	public boolean CompruebaColumna() {
		for (int y = 0; y < 3; y++)
            if (matriz[0][y] == matriz[1][y] && matriz[1][y] == matriz[2][y] && matriz[1][y] != Tipo.blanco) {
                return true;
            }
        return false;
	}
	 public boolean compruebaDiagonal() {
	        if (matriz[0][0] == matriz[1][1] && matriz[1][1] == matriz[2][2] && matriz[1][1] != Tipo.blanco) {
	            return true;
	        }
	        if (matriz[0][2] == matriz[1][1] && matriz[1][1] == matriz[2][0] && matriz[1][1] != Tipo.blanco) {
	            return true;
	        }
	        return false;
	    }
	 


}
