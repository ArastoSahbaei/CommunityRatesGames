import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MatAutocompleteModule,
  MatBadgeModule,
  MatButtonModule,
  MatCardModule,
  MatCheckboxModule,
  MatDialogModule,
  MatFormFieldModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatPaginatorModule,
  MatRadioModule,
  MatSelectModule,
  MatSidenavModule,
  MatSortModule,
  MatTableModule,
  MatToolbarModule
} from "@angular/material";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import {FlexLayoutModule} from "@angular/flex-layout";

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  exports: [
    CommonModule,
    BrowserAnimationsModule,
    MatMenuModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatInputModule,
    MatAutocompleteModule,
    MatFormFieldModule,
    FlexLayoutModule,
    MatCardModule,
    MatGridListModule,
    MatRadioModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatDialogModule,
    MatSelectModule,
    MatBadgeModule
  ],
})
export class MaterialModule { }
