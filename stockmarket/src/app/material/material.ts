import { NgModule } from '@angular/core';
import {MatDatepickerModule, MatButtonModule, MatCheckboxModule, MatTableModule, MatSortModule, MatToolbarModule, MatProgressSpinnerModule, MatFormFieldModule, MatInputModule, MatListModule, MatCardModule, MatSidenavModule, MatSelectModule, MatDialogModule, MatOptionModule} from '@angular/material';
import {MatMenuModule} from '@angular/material/menu';


const MaterialComponents = [
  MatButtonModule,
  MatMenuModule,
  MatTableModule,
  MatSortModule,
  MatProgressSpinnerModule,
  MatToolbarModule,
  MatFormFieldModule,
  MatInputModule,
  MatListModule,
  MatCardModule,
  MatSidenavModule,
  MatSelectModule,
  MatCheckboxModule,
  MatDialogModule,
  MatDatepickerModule,
  MatCardModule,
  MatOptionModule
];

@NgModule({
  imports: [MaterialComponents],
  exports: [MaterialComponents],
})
export class MaterialModule { }