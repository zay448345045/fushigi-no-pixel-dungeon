/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2018 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.fushiginopixel.fushiginopixeldungeon.items.weapon.missiles.darts;

import com.fushiginopixel.fushiginopixeldungeon.Dungeon;
import com.fushiginopixel.fushiginopixeldungeon.actors.Char;
import com.fushiginopixel.fushiginopixeldungeon.actors.EffectType;
import com.fushiginopixel.fushiginopixeldungeon.effects.Lightning;
import com.fushiginopixel.fushiginopixeldungeon.levels.Terrain;
import com.fushiginopixel.fushiginopixeldungeon.sprites.CharSprite;
import com.fushiginopixel.fushiginopixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ShockingDart extends TippedDart {
	
	{
		image = ItemSpriteSheet.SHOCKING_DART;
	}
	
	@Override
	public int procInAttack(Char attacker, Char defender, int damage, EffectType type) {

		int shockingDamage = Random.NormalIntRange(1, 30);
		/*
		if (Dungeon.level.map[defender.pos] == Terrain.WATER) {
			shockingDamage = Random.NormalIntRange(1, 60);
		}else{
			shockingDamage = Random.NormalIntRange(1, 20);
		}
		*/
		defender.damage(shockingDamage, this, new EffectType(type.attachType, EffectType.ELETRIC));
		//defender.damage(Random.NormalIntRange(8, 12), this, new EffectType(type.attachType,EffectType.ELETRIC));
		
		CharSprite s = defender.sprite;
		if (s != null && s.parent != null) {
			ArrayList<Lightning.Arc> arcs = new ArrayList<>();
			arcs.add(new Lightning.Arc(new PointF(s.x, s.y + s.height / 2), new PointF(s.x + s.width, s.y + s.height / 2)));
			arcs.add(new Lightning.Arc(new PointF(s.x + s.width / 2, s.y), new PointF(s.x + s.width / 2, s.y + s.height)));
			s.parent.add(new Lightning(arcs, null));
		}
		
		return super.procInAttack(attacker, defender, damage, type);
	}
}
