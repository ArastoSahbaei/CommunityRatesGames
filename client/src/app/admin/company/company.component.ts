import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../../shared/service/api.service";
import {Company} from "../../shared/interface/company.interface";

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {

  public registerForm: FormGroup;

  constructor(private fb: FormBuilder,
              private api: ApiService) {
  }

  ngOnInit() {
    this.registerForm = this.fb.group({
      'city': ['', [Validators.required]],
      'companyName': ['', [Validators.required]],
      'country': ['', [Validators.required]]
    });
  }

  addCompany() {
    const company = {} as Company;
    console.log(this.registerForm);
    company.city = this.registerForm.value.city;
    company.companyName = this.registerForm.value.companyName;
    company.country = this.registerForm.value.country;

    this.api.createCompany(company).subscribe((response) => {
      console.log(response);
    },
      error => {
      throw error;
      }
    )

  }

  get companyName() {
    return this.registerForm.get('companyName');
  }

  get city() {
    return this.registerForm.get('city');
  }

  get country() {
    return this.registerForm.get('country');
  }
}
