import { NgModule } from '@angular/core';
import {MatButtonModule, MatTableModule, MatSortModule, MatToolbarModule, MatProgressSpinnerModule, MatFormFieldModule, MatInputModule, MatListModule, MatCardModule} from '@angular/material';
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
  MatCardModule
];

@NgModule({
  imports: [MaterialComponents],
  exports: [MaterialComponents],
})
export class MaterialModule { }