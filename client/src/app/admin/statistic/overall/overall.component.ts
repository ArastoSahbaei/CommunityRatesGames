import { Component, OnInit } from '@angular/core';
import {Thumbnail} from "../../../shared/interface/thumbnail";

@Component({
  selector: 'app-overall',
  templateUrl: './overall.component.html',
  styleUrls: ['./overall.component.css']
})
export class OverallComponent implements OnInit {

  thumbnails: Thumbnail[] = [
    { cols:1, rows: 1, picture: "assets/images/charts1.png", tooltip: "New games added this week"},
    { cols:1, rows: 1, picture: "assets/images/charts2.png", tooltip: "Site statistics", link: "/sitestatistic"},
    { cols:1, rows: 1, picture: "assets/images/charts3.png", tooltip: "User statistic", link: "/admin/userstatistic"},
    { cols:1, rows: 1, picture: "assets/images/charts4.png", tooltip: "Comment statistics"},
    { cols:1, rows: 1, picture: "assets/images/charts1.png", tooltip: "tbd"},
    { cols:1, rows: 1, picture: "assets/images/charts2.png", tooltip: "tbd"},
    { cols:1, rows: 1, picture: "assets/images/charts3.png", tooltip: "tbd"},
    { cols:1, rows: 1, picture: "assets/images/charts4.png", tooltip: "tbd"},
  ];

  constructor() { }

  ngOnInit() {
  }

}
