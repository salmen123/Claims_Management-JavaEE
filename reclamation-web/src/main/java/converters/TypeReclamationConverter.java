package converters;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import persistance.TypeReclamation;
import services.reclamation.ReclamationServiceLocal;

@ManagedBean
public class TypeReclamationConverter implements Converter {
	
	@EJB
	private ReclamationServiceLocal reclamationServiceLocal;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		
		TypeReclamation eqTypeReclamation = null;
		if (!arg2.trim().equals("")) {
			eqTypeReclamation  = reclamationServiceLocal.chercherTypeReclamationParType(arg2);
		}
		
		return eqTypeReclamation;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		
		String eqString = null;
		if (arg2 == null || arg2.equals("")) {
			eqString = "";
		}else{
			eqString = ((TypeReclamation)arg2).getType();
		}
		return eqString;
	}

}
