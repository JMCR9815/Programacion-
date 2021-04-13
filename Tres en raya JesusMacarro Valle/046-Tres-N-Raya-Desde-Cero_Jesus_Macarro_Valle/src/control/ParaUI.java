package control;

import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modelo.Tablero;
import utiles.RespuestaColocacion;
import vista.UI;

public class ParaUI extends UI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ActionListener acctionListener;
	private Controlador control;
	private boolean ultimaFicha = true;

	public ParaUI() {
		super();
		this.preparameEsaBotonera();
		control = new Controlador();
	}

	private void preparameEsaBotonera() {
		// Si dos acciones est�n vinculadas secuencialmente
		// deben estar en su propio metodo
		this.crearActionListenerParaBotones();
		this.addEventosALaBotonera();
	}

	private void crearActionListenerParaBotones() {
		this.acctionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// objeto componente que dispara el evento
				JButton boton = (JButton) e.getSource();
				boton.getName().split(":");
//				System.out.println("posicion x" + split[0]);
//				System.out.println("posicion y" + split[1]);
				// llegare aqui cuando alguien hay pulsado un boton
//				de la botonera
				RespuestaColocacion respuestaColocacion = control.realizarJugada(boton.getName());
				RespuestaColocacion TresEnRalla = control.ComprobarTresEnRalla();
				if (!TresEnRalla.isRespuesta() && ultimaFicha) {
					if (respuestaColocacion.isRespuesta()) {
						// si estoy aqui es porque ha habido un cambio
						// por lo tanto debo mostrarlo
						boton.setText(respuestaColocacion.getTipo().getNombre());
					}
					lblMensaje.setText(respuestaColocacion.getMensaje());
				} else {
					lblMensaje.setText(TresEnRalla.getMensaje());
					if (ultimaFicha) {
						boton.setText(respuestaColocacion.getTipo().getNombre());
						ultimaFicha = false;
						
						

					}
					
					JButton btnReiniciar = new JButton("Reiniciar ");
					btnReiniciar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							preparameEsaBotonera();;
							
						}
					});
					btnReiniciar.setBounds(10, 88, 85, 21);
					contentPane.add(btnReiniciar);
					
				}

			}

		};
	}

	private void addEventosALaBotonera() {
		Component[] bartolo = this.botonera.getComponents();
		// os presento, otra vez, a la estructura foreach
		for (Component component : bartolo) {
			((JButton) component).addActionListener(this.acctionListener);
			
		}
	}

}
