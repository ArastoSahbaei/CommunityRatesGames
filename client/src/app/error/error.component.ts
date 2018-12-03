import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Errorlayout} from "../shared/interface/errorlayout.interface";

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  errorMessage: string = "";
  tiles: Errorlayout[] = [
    {cols: 2, rows: 1, text: "Test"},
    {cols: 1, rows: 1, text: "Temp"}
  ];

  constructor(private router: ActivatedRoute) { }

  ngOnInit() {
    this.router.fragment.subscribe(data => this.showHelpfulErrorMessage(data));
  }

  showHelpfulErrorMessage(errorMessage: string) {
      switch (errorMessage) {
        case '400':
          // Bad Request
          this.errorMessage = "Sorry, we couldn't understand what you were sending to us!";
          break;
        case '401':
          // Unauthorized
          this.errorMessage = "You do not have access rights here";
          break;
        case '403':
          // Forbidden
          this.errorMessage = "This section is forbidden for you.";
          break;
        case '404':
          // Not found
          this.errorMessage = "We couldn't find the data you were looking for!";
          break;
        case '406':
          // Not Acceptable
          this.errorMessage = "We couldn't process your data!";
          break;
        case '408':
          // Request Timeout
          this.errorMessage = "Your request timed out. Please try again";
          break;
        case '409':
          // Conflict
          this.errorMessage = "Your request conflicted somewhere. Please try again";
          break;
        case '410':
          // Gone
          this.errorMessage = "Whatever you were looking for is now gone in the wind!";
          break;
        case '500':
          // Internal Server Error
          this.errorMessage = "The server did not respond. Please wait while we try to set it back up!";
          break;
        case '503':
          //  Service Unavailable
          this.errorMessage = "Right now we have too many requests pending. Please try again later";
          break;
      }
  }

}
