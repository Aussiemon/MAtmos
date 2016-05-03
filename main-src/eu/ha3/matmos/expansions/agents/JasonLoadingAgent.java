package eu.ha3.matmos.expansions.agents;

import java.util.Scanner;

import eu.ha3.matmos.engine.core.implem.Knowledge;
import eu.ha3.matmos.expansions.ExpansionIdentity;
import eu.ha3.matmos.tools.JasonExpansions_Engine1Deserializer2000;

/*
--filenotes-placeholder
*/

public class JasonLoadingAgent implements LoadingAgent
{
	@Override
	public boolean load(ExpansionIdentity identity, Knowledge knowledge)
	{
		//Solly edit - resource leak
		Scanner sc = null;
		try
		{
			sc = new Scanner(identity.getPack().getInputStream(identity.getLocation()));
			String jasonString = sc.useDelimiter("\\Z").next();
			return new JasonExpansions_Engine1Deserializer2000().loadJson(jasonString, identity, knowledge);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		} finally {
			if (sc != null) sc.close();
		}
	}
	
}
