# Neutrino Alert architecture (study version)

This implementation uses two bounded contexts:

- `detection`: all NeutrinoAlert-specific domain concepts and implementation layers.
- `shared`: infrastructural cross-cutting components. In an actual exam, **reuse the teacher-provided shared code from the base project instead of replacing it with these illustrative classes**.

```text
POST /api/v1/neutrino-alerts
   -> NeutrinoAlertsController
   -> CreateNeutrinoAlertCommandAssembler
   -> NeutrinoAlertCommandService (Result<NeutrinoAlert>)
   -> NeutrinoAlertRepository (domain port)
   -> NeutrinoAlertRepositoryAdapter
   -> Spring Data JPA / NeutrinoAlertJpaEntity -> MySQL icecube.neutrino_alerts
   -> NeutrinoAlertResourceAssembler + HTTP response
```

Business rules live in the domain aggregate and value objects, not in the JPA entity or controller. The `alert_code` column is unique at the database level. Creation stores `created_at` and `updated_at` as UTC instants. All responses contain primitive flattened fields. Error localization uses `Accept-Language` (English default; Spanish for es/es-PE).

The single POST endpoint is the command side of CQRS. No read-side endpoints are required.
