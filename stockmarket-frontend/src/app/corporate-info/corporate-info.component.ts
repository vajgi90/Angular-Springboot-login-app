import { Component, OnInit } from '@angular/core';
import { Form, NgForm } from '@angular/forms';

@Component({
  selector: 'app-corporate-info',
  templateUrl: './corporate-info.component.html',
  styleUrls: ['./corporate-info.component.css']
})
export class CorporateInfoComponent implements OnInit {

  symbol = '';

  constructor() { }

  ngOnInit() {
  }

}
