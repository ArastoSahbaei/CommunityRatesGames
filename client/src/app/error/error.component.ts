import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  errorMessage: string = "";

  constructor(private router: ActivatedRoute) { }

  ngOnInit() {
    this.router.fragment.subscribe(data => this.showHelpfulErrorMessage(data));
  }

  showHelpfulErrorMessage(errorMessage: string) {
      switch (errorMessage) {
        case '404':
          this.errorMessage = "We couldn't find the data you were looking for!";
          break;
        case '406':
          this.errorMessage = "We couldn't process your data!";
          break;
      }
  }

}
