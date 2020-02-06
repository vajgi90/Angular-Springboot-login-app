import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Company } from '../model/company';

@Component({
  selector: 'app-corporate-specific',
  templateUrl: './corporate-specific.component.html',
  styleUrls: ['./corporate-specific.component.css']
})
export class CorporateSpecificComponent implements OnInit, OnChanges {
  constructor() {}

  @Input() symbol: string;

  companyName: string;
  longDescription: string;
  industry: string;
  sector: string;
  website: string;
  ceo: string;
  employees: number;

  companyData: Company[] = [
    {
      symbol: 'MSFT',
      companyName: 'Microsoft Corp.',
      longDescription:
        'Microsoft Corp. engages in the development and support of software, services, devices, and solutions. It operates through the following business segments: Productivity and Business Processes; Intelligent Cloud; and More Personal Computing. The Productivity and Business Processes segment comprises products and services in the portfolio of productivity, communication, and information services of the company spanning a variety of devices and platform. The Intelligent Cloud segment refers to the public, private, and hybrid serve products and cloud services of the company which can power modern business. The More Personal Computing segment encompasses products and services geared towards the interests of end users, developers, and IT professionals across all devices. The firm also offers operating systems; cross-device productivity applications; server applications; business solution applications; desktop and server management tools; software development tools; video games; personal computers, tablets; gaming and entertainment consoles; other intelligent devices; and related accessories. The company was founded by Paul Gardner Allen and William Henry Gates III in 1975 and is headquartered in Redmond, WA.',
      industry: 'Packaged Software',
      sector: 'Technology Services',
      website: 'http://www.microsoft.com',
      ceo: 'Satya Nadella',
      employees: 144000
    },
    {
      symbol: 'TSLA',
      companyName: 'Tesla, Inc.',
      longDescription:
        'Tesla, Inc. engages in the design, development, manufacture, and sale of fully electric vehicles, energy generation and storage systems. It also provides vehicle service centers, supercharger station, and self-driving capability. The company operates through Automotive, and Energy Generation and Storage segments. The Automotive segment includes the design, development, manufacture and sale of electric vehicles. The Energy Generation and Storage segment includes the design, manufacture, installation, sale, and lease of stationary energy storage products and solar energy systems, and sale of electricity generated by its solar energy systems to customers. It develops energy storage products for use in homes, commercial facilities and utility sites. The company was founded by Jeffrey B. Straubel, Elon Reeve Musk, Martin Eberhard, and Marc Tarpenning on July 1, 2003 and is headquartered in Palo Alto, CA.',
      industry: 'Motor Vehicles',
      sector: 'Consumer Durables',
      website: 'http://www.tesla.com',
      ceo: 'Elon Reeve Musk',
      employees: 48817
    },
    {
      symbol: 'AMZN',
      companyName: 'Amazon.com, Inc.',
      longDescription:
        'Amazon.com, Inc. engages in the provision of online retail shopping services. It operates through the following business segments: North America, International, and Amazon Web Services (AWS). The North America segment includes retail sales of consumer products and subscriptions through North America-focused websites such as www.amazon.com and www.amazon.ca. The International segment offers retail sales of consumer products and subscriptions through internationally-focused websites. The Amazon Web Services segment involves in the global sales of compute, storage, database, and AWS service offerings for start-ups, enterprises, government agencies, and academic institutions. The company was founded by Jeffrey P. Bezos in July 1994 and is headquartered in Seattle, WA.',
      industry: 'Internet Retail',
      sector: 'Retail Trade',
      website: 'http://www.amazon.com',
      ceo: 'Jeffrey P. Bezos',
      employees: 798000
    },
    {
      symbol: 'GOOGL',
      companyName: 'Alphabet, Inc.',
      longDescription:
      'Alphabet, Inc. is a holding company, which engages in the business of acquisition and operation of different companies. It operates through the Google and Other Bets segments. The Google segment includes its main Internet products such as Ads, Android, Chrome, Commerce, Google Cloud, Google Maps, Google Play, Hardware, Search, and YouTube. The Other Bets segment consists of businesses such as Access, Calico, CapitalG, GV, Nest, Verily, Waymo, and X. The company was founded by Lawrence E. Page and Sergey Mikhaylovich Brin on October 2, 2015 and is headquartered in Mountain View, CA.',
      industry: 'Internet Software/Services',
      sector: 'Technology Services',
      website: 'http://abc.xyz',
      ceo: 'Sundar Pichai',
      employees: 118899
    },
    {
      symbol: 'AAPL',
      companyName: 'Apple, Inc.',
      longDescription:
        'Apple, Inc. engages in the design, manufacture, and sale of smartphones, personal computers, tablets, wearables and accessories, and other variety of related services. It operates through the following geographical segments: Americas, Europe, Greater China, Japan, and Rest of Asia Pacific. The Americas segment includes North and South America. The Europe segment consists of European countries, as well as India, the Middle East, and Africa. The Greater China segment comprises of China, Hong Kong, and Taiwan. The Rest of Asia Pacific segment includes Australia and Asian countries. Its products and services include iPhone, Mac, iPad, AirPods, Apple TV, Apple Watch, Beats products, Apple Care, iCloud, digital content stores, streaming, and licensing services. The company was founded by Steven Paul Jobs, Ronald Gerald Wayne, and Stephen G. Wozniak on April 1, 1976 and is headquartered in Cupertino, CA.',
      industry: 'Telecommunications Equipment',
      sector: 'Electronic Technology',
      website: 'http://www.apple.com',
      ceo: 'Timothy Donald Cook',
      employees: 137000
    },
    {
      symbol: 'FB',
      companyName: 'Facebook, Inc.',
      longDescription:
        'Facebook, Inc. operates as a social networking company worldwide. The company engages in the development of social media applications for people to connect through mobile devices, personal computers, and other surfaces. It enables users to share opinions, ideas, photos, videos, and other activities online. The firm\'s products include Facebook, Instagram, Messenger, WhatsApp, and Oculus. The company was founded by Mark Elliot Zuckerberg, Dustin Moskovitz, Chris R. Hughes, Andrew McCollum, and Eduardo P. Saverin on February 4, 2004 and is headquartered in Menlo Park, CA.',
      industry: 'Internet Software/Services',
      sector: 'Technology Services',
      website: 'http://www.facebook.com',
      ceo: 'Mark Elliot Zuckerberg',
      employees: 44942
    }
  ];

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    console.log(this.symbol);
    switch (this.symbol) {
      case 'MSFT': {
        this.companyName = this.companyData[0].companyName;
        this.longDescription = this.companyData[0].longDescription;
        this.industry = this.companyData[0].industry;
        this.sector = this.companyData[0].sector;
        this.website = this.companyData[0].website;
        this.ceo = this.companyData[0].ceo;
        this.employees = this.companyData[0].employees;
        console.log(this.companyName);
        break;
      }
      case 'TSLA': {
        this.companyName = this.companyData[1].companyName;
        this.longDescription = this.companyData[1].longDescription;
        this.industry = this.companyData[1].industry;
        this.sector = this.companyData[1].sector;
        this.website = this.companyData[1].website;
        this.ceo = this.companyData[1].ceo;
        this.employees = this.companyData[1].employees;
        break;
      }
      case 'AMZN': {
        this.companyName = this.companyData[2].companyName;
        this.longDescription = this.companyData[2].longDescription;
        this.industry = this.companyData[2].industry;
        this.sector = this.companyData[2].sector;
        this.website = this.companyData[2].website;
        this.ceo = this.companyData[2].ceo;
        this.employees = this.companyData[2].employees;
        break;
      }
      case 'GOOGL': {
        this.companyName = this.companyData[3].companyName;
        this.longDescription = this.companyData[3].longDescription;
        this.industry = this.companyData[3].industry;
        this.sector = this.companyData[3].sector;
        this.website = this.companyData[3].website;
        this.ceo = this.companyData[3].ceo;
        this.employees = this.companyData[3].employees;
        break;
      }
      case 'AAPL': {
        this.companyName = this.companyData[4].companyName;
        this.longDescription = this.companyData[4].longDescription;
        this.industry = this.companyData[4].industry;
        this.sector = this.companyData[4].sector;
        this.website = this.companyData[4].website;
        this.ceo = this.companyData[4].ceo;
        this.employees = this.companyData[4].employees;
        break;
      }
      case 'FB': {
        this.companyName = this.companyData[5].companyName;
        this.longDescription = this.companyData[5].longDescription;
        this.industry = this.companyData[5].industry;
        this.sector = this.companyData[5].sector;
        this.website = this.companyData[5].website;
        this.ceo = this.companyData[5].ceo;
        this.employees = this.companyData[5].employees;
        break;
      }
    }
  }
}