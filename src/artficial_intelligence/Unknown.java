package artficial_intelligence;

import window.Stage1;
import model.Ennemi;
import model.SkinProvider;
import model.Weapon;

public class Unknown  extends artificial_intelligence{

	@Override
	public void update(Ennemi en) {if(en.getPosY()<550  && en.isToto() == false)
		en.setPosY(en.getPosY()+2);
	else if(en.getPosY()>50)
		en.setPosY(en.getPosY()-2);
	if (en.getPosY()>=550)
		en.setToto(true);
	if (en.getPosY()<=50)
		en.setToto(false);
	 
	  int random = (int)(Math.random() * 200) ;
	  if(random >197 )
	Stage1.missiles.add(new Weapon("missile",SkinProvider.ALIEN_WEAPON, en.getPosX(), en.getPosY(), en,Weapon.LEFT));
	
	 en.getBound().setLocation(en.getPosX(), en.getPosY());	
	}

}
