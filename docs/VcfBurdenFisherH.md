# VcfBurdenFisherH

![Last commit](https://img.shields.io/github/last-commit/lindenb/jvarkit.png)

Fisher Case /Controls per Variant


## Usage

```
Usage: vcfburdenfisherh [options] Files
  Options:
    --attribute
      Name of the attribute used for INFO
      Default: BurdenHFisher
    --bcf-output
      If this program writes a VCF to a file, The format is first guessed from 
      the file suffix. Otherwise, force BCF output. The current supported BCF 
      version is : 2.1 which is not compatible with bcftools/htslib (last 
      checked 2019-11-15)
      Default: false
    -F2, --ctrlgtcase
      Set this FILTER if the proportion of Controls carrying a ALT allele is 
      creater than proportion of CASES. if blank, variant is discarded.
      Default: CTRL_CASE_RATIO
    -F1, --filter
      if this value is not blank, the FILTER will be set for this variant if 
      the fisher values are out of the bounds.
      Default: BurdenHFisher
    --generate-vcf-md5
      Generate MD5 checksum for VCF output.
      Default: false
    -gtf, --gtf, --gtFiltered
      [20180115] Ignore FILTERed **Genotype**
      Default: false
    -h, --help
      print help and exit
    --helpFormat
      What kind of help. One of [usage,markdown,xml].
    -ignoreFiltered, --ignoreFiltered
      [20171031] Don't try to calculate things why variants already FILTERed 
      (faster) 
      Default: false
    -M, --max-fisher
      max inclusive value of fisher test. A decimal number between 0.0 and 
      1.0. If the value ends with '%' it is interpretted as a percentage eg. 
      '1%' => '0.01'. A slash '/' is interpretted as a ratio. e.g: '1/100' => 
      '0.01'. 
      Default: 1.0
    -m, --min-fisher
      min inclusive value of fisher test. A decimal number between 0.0 and 
      1.0. If the value ends with '%' it is interpretted as a percentage eg. 
      '1%' => '0.01'. A slash '/' is interpretted as a ratio. e.g: '1/100' => 
      '0.01'. 
      Default: 0.0
    -o, --out
      Output file. Optional . Default: stdout
  * -p, --pedigree
      A pedigree file. tab delimited. Columns: family,id,father,mother, 
      sex:(0:unknown;1:male;2:female), phenotype 
      (-9|?|.:unknown;1|affected|case:affected;0|unaffected|control:unaffected) 
    -Q, --qual
      Overwrite QUAL column with the lowest fisher value.
      Default: false
    --report
      [20190418] save report as bed file
    --version
      print version and exit

```


## Keywords

 * vcf
 * burden
 * fisher


## Compilation

### Requirements / Dependencies

* java [compiler SDK 11](https://jdk.java.net/11/). Please check that this java is in the `${PATH}`. Setting JAVA_HOME is not enough : (e.g: https://github.com/lindenb/jvarkit/issues/23 )


### Download and Compile

```bash
$ git clone "https://github.com/lindenb/jvarkit.git"
$ cd jvarkit
$ ./gradlew vcfburdenfisherh
```

The java jar file will be installed in the `dist` directory.


## Creation Date

20160418

## Source code 

[https://github.com/lindenb/jvarkit/tree/master/src/main/java/com/github/lindenb/jvarkit/tools/burden/VcfBurdenFisherH.java](https://github.com/lindenb/jvarkit/tree/master/src/main/java/com/github/lindenb/jvarkit/tools/burden/VcfBurdenFisherH.java)

### Unit Tests

[https://github.com/lindenb/jvarkit/tree/master/src/test/java/com/github/lindenb/jvarkit/tools/burden/VcfBurdenFisherHTest.java](https://github.com/lindenb/jvarkit/tree/master/src/test/java/com/github/lindenb/jvarkit/tools/burden/VcfBurdenFisherHTest.java)


## Contribute

- Issue Tracker: [http://github.com/lindenb/jvarkit/issues](http://github.com/lindenb/jvarkit/issues)
- Source Code: [http://github.com/lindenb/jvarkit](http://github.com/lindenb/jvarkit)

## License

The project is licensed under the MIT license.

## Citing

Should you cite **vcfburdenfisherh** ? [https://github.com/mr-c/shouldacite/blob/master/should-I-cite-this-software.md](https://github.com/mr-c/shouldacite/blob/master/should-I-cite-this-software.md)

The current reference is:

[http://dx.doi.org/10.6084/m9.figshare.1425030](http://dx.doi.org/10.6084/m9.figshare.1425030)

> Lindenbaum, Pierre (2015): JVarkit: java-based utilities for Bioinformatics. figshare.
> [http://dx.doi.org/10.6084/m9.figshare.1425030](http://dx.doi.org/10.6084/m9.figshare.1425030)


## Input

Variants in that VCF should have one and only one ALT allele. Use https://github.com/lindenb/jvarkit/wiki/VcfMultiToOneAllele if needed.

VCF header must contain a pedigree ( see VCFinjectPedigree ) or a pedigree must be defined.


