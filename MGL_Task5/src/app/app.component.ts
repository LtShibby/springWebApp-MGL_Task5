import { Component } from '@angular/core';
import { ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.ShadowDom
})
export class AppComponent {
  title = 'MGL-Task5';

  getUrl() {
    return "url('https://ak6.picdn.net/shutterstock/videos/1024598666/thumb/1.jpg')";
  }
}
