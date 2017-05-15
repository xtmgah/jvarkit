/*
The MIT License (MIT)

Copyright (c) 2014 Pierre Lindenbaum

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


History:
* 2014-11 : handle clipped bases
* 2014 creation

*/
package com.github.lindenb.jvarkit.tools.sam2tsv;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import com.beust.jcommander.Parameter;
import com.github.lindenb.jvarkit.util.jcommander.Launcher;
import com.github.lindenb.jvarkit.util.jcommander.Program;
import com.github.lindenb.jvarkit.util.log.Logger;
import com.github.lindenb.jvarkit.util.picard.GenomicSequence;
import com.github.lindenb.jvarkit.util.picard.SAMSequenceDictionaryProgress;

import htsjdk.samtools.reference.IndexedFastaSequenceFile;
import htsjdk.samtools.Cigar;
import htsjdk.samtools.CigarElement;
import htsjdk.samtools.CigarOperator;
import htsjdk.samtools.SAMUtils;
import htsjdk.samtools.SamReader;
import htsjdk.samtools.SAMRecord;
import htsjdk.samtools.SAMRecordIterator;
import htsjdk.samtools.util.CloserUtil;
/**

BEGIN_DOC

### Output

Columns are:

 *  read name
 *  read flags
 *  reference name
 *  read-pos
 *  read-base
 *  read-qual
 *  ref-pos
 *  ref-base
 *  cigar-op



### Example
 


```
$ java -jar dist/sam2tsv.jar -A  \
    -r samtools-0.1.18/examples/toy.fa 
      samtools-0.1.18/examples/toy.sam
r001	163	ref	0	T	.	7	T	M
r001	163	ref	1	T	.	8	T	M
r001	163	ref	2	A	.	9	A	M
r001	163	ref	3	G	.	10	G	M
r001	163	ref	4	A	.	11	A	M
r001	163	ref	5	T	.	12	T	M
r001	163	ref	6	A	.	13	A	M
r001	163	ref	7	A	.	14	A	M
r001	163	ref	8	A	.	.	.	I
r001	163	ref	9	G	.	.	.	I
r001	163	ref	10	A	.	.	.	I
r001	163	ref	11	G	.	.	.	I
r001	163	ref	12	G	.	15	G	M
r001	163	ref	13	A	.	16	A	M
r001	163	ref	14	T	.	17	T	M
r001	163	ref	15	A	.	18	A	M
r001	163	ref	.	.	.	19	G	D
r001	163	ref	16	C	.	20	C	M
r001	163	ref	17	T	.	21	T	M
r001	163	ref	18	G	.	22	G	M
:   ref        7 TTAGATAAAGAGGATA-CTG 22      
:                ||||||||    |||| |||
:  r001        1 TTAGATAA----GATAGCTG 19      
r002	0	ref	1	A	.	.	.	I
r002	0	ref	2	A	.	.	.	I
r002	0	ref	3	A	.	9	A	M
r002	0	ref	4	G	.	10	G	M
r002	0	ref	5	A	.	11	A	M
r002	0	ref	6	T	.	12	T	M
r002	0	ref	7	A	.	13	A	M
r002	0	ref	8	A	.	14	A	M
r002	0	ref	9	G	.	.	.	I
r002	0	ref	10	G	.	.	.	I
r002	0	ref	11	G	.	15	G	M
r002	0	ref	12	A	.	16	A	M
r002	0	ref	13	T	.	17	T	M
r002	0	ref	14	A	.	18	A	M
r002	0	ref	15	A	.	.	.	I
r002	0	ref	16	A	.	.	.	I
:   ref        8 AAAGATAAGGGATAAA 18      
:                  ||||||  ||||  
:  r002        1 --AGATAA--GATA-- 17      
r003	0	ref	0	A	.	9	A	M
r003	0	ref	1	G	.	10	G	M
r003	0	ref	2	C	.	11	A	M
r003	0	ref	3	T	.	12	T	M
r003	0	ref	4	A	.	13	A	M
r003	0	ref	5	A	.	14	A	M
:   ref        4 AGCTAA 14      
:                || |||
:  r003        1 AGATAA 6       
r004	0	ref	0	A	.	16	A	M
r004	0	ref	1	T	.	17	T	M
r004	0	ref	2	A	.	18	A	M
r004	0	ref	3	G	.	19	G	M
r004	0	ref	4	C	.	20	C	M
r004	0	ref	5	T	.	21	T	M
r004	0	ref	.	.	.	22	G	N
r004	0	ref	.	.	.	23	T	N
r004	0	ref	.	.	.	24	G	N
r004	0	ref	.	.	.	25	C	N
r004	0	ref	.	.	.	26	T	N
r004	0	ref	.	.	.	27	A	N
r004	0	ref	.	.	.	28	G	N
r004	0	ref	.	.	.	29	T	N
r004	0	ref	.	.	.	30	A	N
r004	0	ref	.	.	.	31	G	N
r004	0	ref	.	.	.	32	G	N
r004	0	ref	.	.	.	33	C	N
r004	0	ref	.	.	.	34	A	N
r004	0	ref	.	.	.	35	G	N
r004	0	ref	6	C	.	.	.	I
r004	0	ref	7	T	.	36	T	M
r004	0	ref	8	C	.	37	C	M
r004	0	ref	9	A	.	38	A	M
r004	0	ref	10	G	.	39	G	M
r004	0	ref	11	C	.	40	C	M
:   ref       16 ATAGCT--------------CTCAGC 40      
:                ||||||               |||||
:  r004        1 ATAGCTGTGCTAGTAGGCAG-TCAGC 12      
r003	16	ref	0	T	.	29	T	M
r003	16	ref	1	A	.	30	A	M
r003	16	ref	2	G	.	31	G	M
r003	16	ref	3	G	.	32	G	M
r003	16	ref	4	C	.	33	C	M
:   ref       23 TAGGC 33      
:                |||||
:  r003        1 TAGGC 5       
r001	83	ref	0	C	.	37	C	M
r001	83	ref	1	A	.	38	A	M
r001	83	ref	2	G	.	39	G	M
r001	83	ref	3	C	.	40	C	M
r001	83	ref	4	G	.	41	G	M
r001	83	ref	5	C	.	42	C	M
r001	83	ref	6	C	.	43	C	M
r001	83	ref	7	A	.	44	A	M
r001	83	ref	8	T	.	45	T	M
:   ref       37 CAGCGCCAT 45      
:                |||||||||
:  r001        1 CAGCGCCAT 9       
x1	0	ref2	0	A	30	1	a	M
x1	0	ref2	1	G	30	2	g	M
x1	0	ref2	2	G	30	3	g	M
x1	0	ref2	3	T	30	4	t	M
x1	0	ref2	4	T	30	5	t	M
x1	0	ref2	5	T	30	6	t	M
x1	0	ref2	6	T	30	7	t	M
x1	0	ref2	7	A	30	8	a	M
x1	0	ref2	8	T	30	9	t	M
x1	0	ref2	9	A	30	10	a	M
x1	0	ref2	10	A	30	11	a	M
x1	0	ref2	11	A	30	12	a	M
x1	0	ref2	12	A	30	13	a	M
x1	0	ref2	13	C	30	14	c	M
x1	0	ref2	14	A	30	15	a	M
x1	0	ref2	15	A	30	16	a	M
x1	0	ref2	16	A	30	17	t	M
x1	0	ref2	17	T	30	18	t	M
x1	0	ref2	18	A	30	19	a	M
x1	0	ref2	19	A	30	20	a	M
:  ref2        1 AGGTTTTATAAAACAAATAA 20      
:                |||||||||||||||| |||
:    x1        1 aggttttataaaacaattaa 20      
x2	0	ref2	0	G	30	2	g	M
x2	0	ref2	1	G	30	3	g	M
x2	0	ref2	2	T	30	4	t	M
x2	0	ref2	3	T	30	5	t	M
x2	0	ref2	4	T	30	6	t	M
x2	0	ref2	5	T	30	7	t	M
x2	0	ref2	6	A	30	8	a	M
x2	0	ref2	7	T	30	9	t	M
x2	0	ref2	8	A	30	10	a	M
x2	0	ref2	9	A	30	11	a	M
x2	0	ref2	10	A	30	12	a	M
x2	0	ref2	11	A	30	13	a	M
x2	0	ref2	12	C	30	14	c	M
x2	0	ref2	13	A	30	15	a	M
x2	0	ref2	14	A	30	16	a	M
x2	0	ref2	15	A	30	17	t	M
x2	0	ref2	16	T	30	18	t	M
x2	0	ref2	17	A	30	19	a	M
x2	0	ref2	18	A	30	20	a	M
x2	0	ref2	19	T	30	21	g	M
x2	0	ref2	20	T	30	22	t	M
:  ref2        2 GGTTTTATAAAACAAATAATT 22      
:                ||||||||||||||| ||| |
:    x2        1 ggttttataaaacaattaagt 21      
(...)   

```






### History

 *  Moved to a standard argc/argv command line
 *  2014-04: added qual and samflag. Fixed a bug in soft-clip
 *  2014-11: manage hard+soft clip



### See also

 *  [[Biostar59647]]
 *  https://www.biostars.org/p/157232/


### Citations


Sam2tsv was cited in : 

 *  Illumina TruSeq Synthetic Long-Reads Empower De Novo Assembly and Resolve Complex, Highly-Repetitive Transposable Elements* . McCoy RC, Taylor RW, Blauwkamp TA, Kelley JL, Kertesz M, et al. (2014) Illumina TruSeq Synthetic Long-Reads Empower De Novo Assembly and Resolve Complex, Highly-Repetitive Transposable Elements. PLoS ONE 9(9): e106689. doi: 10.1371/journal.pone.0106689  http://journals.plos.org/plosone/article?id=10.1371/journal.pone.0106689



END_DOC
*/
@Program(name="sam2tsv",
	description="Prints the SAM alignments as a TAB delimited file.",
	keywords={"sam","bam","table","tsv"})
public class Sam2Tsv
	extends Launcher
	{
	private static final Logger LOG = Logger.build(Sam2Tsv.class).make();


	@Parameter(names={"-o","--output"},description= OPT_OUPUT_FILE_OR_STDOUT)
	private File outputFile = null;


	@Parameter(names={"-A","--printAlignments"},description="Print Alignments")
	private boolean printAlignment = false;

	@Parameter(names={"-r","-R","--reference"},description="Indexed fasta Reference",required=true)
	private File refFile = null;
	
	

	private IndexedFastaSequenceFile indexedFastaSequenceFile=null;
	private GenomicSequence genomicSequence=null;
	/** lines for alignments */
	private StringBuilder L1=null;
	private StringBuilder L2=null;
	private StringBuilder L3=null;

	private PrintWriter out = null;
	
	private class Row
		{
		SAMRecord rec;
		byte readbases[];
		byte readQuals[];

		int readPos;
		int refPos;
		CigarOperator op;
		
		public char getRefBase()
			{
			if(refPos>=1 && refPos<= genomicSequence.length())
 				{
				return genomicSequence.charAt(refPos-1);
 				}
			return '.';
			}
		
		public char getReadBase()
			{
			return readPos==-1 || this.readPos>=this.readbases.length?'.':(char)this.readbases[this.readPos];
			}
		public char getReadQual()
			{
			byte c= readPos==-1 || this.readQuals==null || this.readPos>=this.readQuals.length?(byte)0:this.readQuals[this.readPos];
			return SAMUtils.phredToFastq(c);
			}
		
		}
	
	private void writeAln(final Row row)
			{
			char c1;
			char c3;
			this.out.print(row.rec.getReadName());
			this.out.print("\t");
			this.out.print(row.rec.getFlags());
			this.out.print("\t");
			this.out.print(row.rec.getReadUnmappedFlag()?".":row.rec.getReferenceName());
			this.out.print("\t");
			if(row.readPos!=-1)
				{
				c1 =row.getReadBase();
				this.out.print(row.readPos);
				this.out.print("\t");
				this.out.print(c1);
				this.out.print("\t");
				this.out.print(row.getReadQual());
				this.out.print("\t");
				}
			else
				{
				c1= '-';
				this.out.print(".\t.\t.\t");
				}
			
			if(row.refPos != -1)
				{
				c3 = row.getRefBase();
				this.out.print(row.refPos);
				this.out.print("\t");
				this.out.print(c3);
				this.out.print("\t");
				}
			else
				{
				c3= '-';
				this.out.print(".\t.\t");
				}
			this.out.print(row.op==null?".":row.op.name());
			this.out.println();
			
			if(this.printAlignment)
				{
				L1.append(c1);
				L3.append(c3);
				
				if(Character.isLetter(c1) &&  Character.toUpperCase(c1)== Character.toUpperCase(c3))
					{
					L2.append('|');
					}
				else
					{
					L2.append(' ');
					}
				}
			}
	
	private void printAln(final Row row)
		{
		final SAMRecord rec = row.rec;
		if(rec==null) return;
		Cigar cigar=rec.getCigar();
		if(cigar==null) return;
		
		row.readbases = rec.getReadBases();
		row.readQuals = rec.getBaseQualities();
		if(row.readbases==null )
			{
			row.op=null;
			row.refPos=-1;
			row.readPos=-1;
			writeAln(row);
			return;
			}
		if(rec.getReadUnmappedFlag())
			{
			row.op=null;
			row.refPos=-1;
			for(int i=0;i< row.readbases.length;++i)
				{
				row.readPos=i;
				writeAln(row);
				}
			return;
			}
		
		//fix hard clipped reads
		StringBuilder fixReadBases=new StringBuilder(row.readbases.length);
		StringBuilder fixReadQuals=new StringBuilder(row.readbases.length);
		int readIndex = 0;
		for (final CigarElement ce : cigar.getCigarElements())
		 {
		 CigarOperator op= ce.getOperator();
		 
		 for(int i=0;i< ce.getLength();++i)
			{
			if(op.equals(CigarOperator.H))
				{
				
				fixReadBases.append('*');
				fixReadQuals.append('*');
				}
			else if(!op.consumesReadBases())
				{
				break;
				}
			else
				{
				fixReadBases.append((char)row.readbases[readIndex]);
				fixReadQuals.append(
						row.readQuals==null ||
						row.readQuals.length<=readIndex ?
						'*':(char)row.readQuals[readIndex]);
				readIndex++;
				}
			}
		 }
		row.readbases = fixReadBases.toString().getBytes();
		row.readQuals = fixReadQuals.toString().getBytes();

		
		if(genomicSequence==null || !genomicSequence.getChrom().equals(rec.getReferenceName()))
			{
			genomicSequence=new GenomicSequence(this.indexedFastaSequenceFile, rec.getReferenceName());
			}
		

		 readIndex = 0;
		 int refIndex = rec.getUnclippedStart();
		 				 
		 for (final CigarElement e : cigar.getCigarElements())
			 {
			 row.op=e.getOperator();
			 
			 switch (e.getOperator())
				 {
				 case S :
				 case H : //length of read has been fixed previously, so same as 'S'
					 	{
					 	
				 		for(int i=0;i<e.getLength();++i)
				 			{
				 			row.readPos=  readIndex;
				 			row.refPos  = refIndex;
			 				writeAln(row);
				 			readIndex++;
				 			refIndex++;//because we used getUnclippedStart
				 			}
						break; 
					 	}
				 case P : 
					 	{
					 	row.refPos  = -1;
					 	row.readPos = -1;
				 		for(int i=0;i<e.getLength();++i)
				 			{
				 			writeAln(row);
				 			}
						break; 
					 	}
				 case I :
				 		{
				 		row.refPos  = -1;
				 		for(int i=0;i<e.getLength();++i)
				 			{
				 			row.readPos=readIndex;
				 			writeAln(row);
				 			readIndex++;
				 			}
				 		break;
				 		}
				 case N :  //cont. -- reference skip
				 case D :
				 		{
				 		row.readPos  = -1;
				 		for(int i=0;i<e.getLength();++i)
				 			{
				 			row.refPos = refIndex;
				 			writeAln(row);
				 			refIndex++;
				 			}
				 		break;
				 		}
				 case M :
				 case EQ :
				 case X :
			 			{
				 		for(int i=0;i< e.getLength();++i)
				 			{
				 			row.refPos = refIndex;
				 			row.readPos = readIndex;
				 			writeAln(row);
				 			refIndex++;
				 			readIndex++;
				 			}
				 		break;
			 			}
					
				 default : throw new IllegalStateException("Case statement didn't deal with cigar op: " + e.getOperator());
				 }

			 }
	
		
		
		 if(printAlignment)
				{
				
				int len=Math.max(rec.getReadNameLength(), rec.getReferenceName().length())+2;

				this.out.printf(":%"+len+"s %8d %s %-8d\n",
						rec.getReferenceName(),
						rec.getUnclippedStart(),
						L3.toString(),
						rec.getUnclippedEnd()
						);
				this.out.printf(":%"+len+"s %8s %s\n",
						"",
						"",
						L2.toString()
						);

				this.out.printf(":%"+len+"s %8d %s %-8d\n",
						rec.getReadName(),
						1,
						L1.toString(),
						rec.getReadLength()
						);

				L1.setLength(0);
				L2.setLength(0);
				L3.setLength(0);
				}

		}
	
	
	
	private void scan(final SamReader r) 
		{
		final Row row=new Row();
		SAMRecordIterator iter=null;
		try{
			final SAMSequenceDictionaryProgress progress=new SAMSequenceDictionaryProgress(r.getFileHeader());
			iter=r.iterator();	
			while(iter.hasNext())
				{
				row.rec =progress.watch(iter.next());
				printAln(row);
				if(this.out.checkError()) break;
				}
			progress.finish();
			}
		catch(final Exception err)
			{
			LOG.error("scan error:",err);
			throw new RuntimeException(String.valueOf(err.getMessage()),err);
			}
		finally
			{
			CloserUtil.close(iter);
			}
		}
	@Override
	public int doWork(List<String> args) {
		if(this.refFile==null)
			{
			LOG.error("reference.undefined");
			return -1;
			}
		
		if(printAlignment)
			{
			L1=new StringBuilder();
			L2=new StringBuilder();
			L3=new StringBuilder();
			}
		
		SamReader samFileReader=null;
		try
			{
			
			this.indexedFastaSequenceFile=new IndexedFastaSequenceFile(refFile);
			out  =  openFileOrStdoutAsPrintWriter(outputFile);
			out.println("#READ_NAME\tFLAG\tCHROM\tREAD_POS\tBASE\tQUAL\tREF_POS\tREF\tOP");
			samFileReader= openSamReader(oneFileOrNull(args));
			
			scan(samFileReader);
			samFileReader.close();
			samFileReader = null;
			this.out.flush();
			return RETURN_OK;
			}
		catch (final Exception e)
			{
			LOG.error(e);
			return -1;
			}
		finally
			{
			CloserUtil.close(indexedFastaSequenceFile);
			CloserUtil.close(samFileReader);
			CloserUtil.close(out);
			L1=null;
			L2=null;
			L3=null;
			}
		}
	
	public static void main(String[] args)
		{
		new Sam2Tsv().instanceMainWithExit(args);
		}
}
