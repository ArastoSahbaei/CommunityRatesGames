import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {TopGames} from "../../shared/interface/top-games.interface";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../../shared/service/api.service";
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";

@Component({
  selector: 'app-top100',
  templateUrl: './top100.component.html',
  styleUrls: ['./top100.component.css']
})
export class Top100Component implements OnInit, AfterViewInit {

  games: TopGames[] = [];
  dataSource = new MatTableDataSource(this.games);
  tableColumns: string[] = ['title', 'company', 'genre', 'rating'];

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private api: ApiService) {
    this.api.getTop100().subscribe((response) =>{
      this.games = Object.values(response);
      console.log(response);
      this.dataSource.data = this.games;
    }, error => {
      throw error;
    });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  ngOnInit() {
  }
}
