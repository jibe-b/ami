package org.xmlcml.ami2.plugins.dummy;

import java.util.List;

import nu.xom.Element;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.ami2.plugins.AMIArgProcessor;
import org.xmlcml.ami2.plugins.AMISearcher;
import org.xmlcml.ami2.plugins.NamedPattern;
import org.xmlcml.cmine.files.ResultElement;
import org.xmlcml.cmine.files.ResultsElement;

public class DummySearcher extends AMISearcher {

	
	public static final Logger LOG = Logger.getLogger(DummySearcher.class);
	static {
		LOG.setLevel(Level.DEBUG);
	}

	public DummySearcher(AMIArgProcessor argProcessor, NamedPattern namedPattern) {
		super(argProcessor, namedPattern);
	}

	@Override 
	public String getValue(Element xomElement) {
		String xmlString = xomElement.toXML();
		return xmlString;
	}

	@Override
	public ResultsElement search(List<? extends Element> elements) {
		ResultsElement resultsElement = new DummyResultsElement();
		for (Element element : elements) {
			String xmlString = getValue(element);
			LOG.trace(xmlString);
			List<ResultElement> resultElementList = this.search(xmlString);
			for (ResultElement resultElement : resultElementList) {
				resultsElement.appendChild(resultElement);
			}
		}
		return resultsElement;
	}

	/**
	 *  //PLUGIN
	 */
	public DummyResultElement createResultElement() {
		return new DummyResultElement();
	}

}
