package xyz.marstonconnell.graphics.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollisionHandler {
	private List<Drawable> items;
	
	public CollisionHandler() {
		items = new ArrayList<Drawable>();
	}
	
	/**
	 * Prototype for collision checking
	 * @return
	 */
	public List<Map<Drawable, List<Drawable>>> checkCollisions() {
		List<Map<Drawable, List<Drawable>>> returns = new ArrayList<Map<Drawable, List<Drawable>>>();
		Drawable collides;
		List<Drawable> collidesWith = new ArrayList<Drawable>();
		
		for(int i = 0; i < items.size(); i ++) {
			collides = items.get(i);
			
			for(int j = 0; j < items.size(); j ++) {
				if(collides.intersects(items.get(j)) && !items.get(j).equals(collides)) {
					collidesWith.add(items.get(j));
				}
				
			}
			
			if(!collidesWith.isEmpty()) {
				HashMap<Drawable, List<Drawable>> hm = new HashMap();
				hm.put(collides, collidesWith);
				returns.add(hm);
			}
		}
		
		
		return returns;
		
	}
}
