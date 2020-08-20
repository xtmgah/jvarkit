/*
The MIT License (MIT)

Copyright (c) 2020 Pierre Lindenbaum

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.github.lindenb.jvarkit.util.bio.bed;

import htsjdk.samtools.util.StringUtil;
import htsjdk.tribble.AsciiFeatureCodec;
import htsjdk.tribble.readers.LineIterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.lindenb.jvarkit.lang.CharSplitter;
import com.github.lindenb.jvarkit.util.log.Logger;


public class BedLineCodec
	extends AsciiFeatureCodec<BedLine>
	{
	private static final Logger LOG = Logger.build(BedLineCodec.class).make();
	private final CharSplitter tab= CharSplitter.TAB;
	public BedLineCodec() {
		super(BedLine.class);
		}
	
	@Override
	/** may be null of line is a BEd header or empty string */
	public BedLine decode(final String line) {
		if (StringUtil.isBlank(line)) {
            return null;
        	}
		if(BedLine.isBedHeader(line)) return null;
		
        final String[] tokens = this.tab.split(line);
        if(tokens.length<2) {
        	LOG.warn("not enough tokens in BED line "+line+" (\""+line.replaceAll("[\t]", "(tab)")+"\"=. Skipping.");
        	return null;
        	}
        if(tokens[1].equals(tokens[2])) {
        	LOG.warn("cannot use empty BED interval "+line+" (\""+line.replaceAll("[\t]", "(tab)")+"\"=. Skipping.");
        	return null;
        	}
        return new BedLine(tokens);
        }
	
	
	/** return   a List of Strings containing the line of
	 * the bed header "browser.."
	 * Never null but may be empty;
	 */
	@Override
	public List<String> readActualHeader(final LineIterator reader) {
		List<String> header=null;
		while(reader.hasNext())
			{
			final String line = reader.peek();
			if(line==null || !BedLine.isBedHeader(line)) break;
			if(header==null) header=new ArrayList<>();
			header.add(reader.next());				
			}
		return header==null?Collections.emptyList():header;
		}
	
    @Override
    public boolean canDecode(final String path) {
        return path.toLowerCase().endsWith(".bed");
    }

}
