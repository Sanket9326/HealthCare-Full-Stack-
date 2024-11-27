import { TestBed } from '@angular/core/testing';

import { CredentialsServiceService } from './credentials-service.service';

describe('CredentialsServiceService', () => {
  let service: CredentialsServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CredentialsServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
