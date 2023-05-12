/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clients.asteroids.messages;

import java.io.Serializable;

/**
 *
 * @author aleja
 */
public class newShip implements Serializable {

	private static final long serialVersionUID = 6788857672671689167L;

	public int shipId;
	public boolean accelerate;
	
}
