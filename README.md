# Seguradora AUDSAT

Projeto feito como teste técnico Java do processo seletivo para a vaga de desenvolvedor backend da Audsat.

## Deploy

Para fazer o deploy desse projeto rode, dentro da pasta do projeto:

```bash
  docker-compose up
```

## Documentação da API

#### Cadastro de Orçamento

```
  POST /seguradora-audsat/insurance/budget
```

##### Request

```json
{
  "idCustomer": 1,
  "idCar": 1,
  "isActive": true
}
```

| Parâmetro    | Tipo      | Descrição                                |
|:-------------|:----------|:-----------------------------------------|
| `idCustomer` | `number`  | **Obrigatório**. ID do cliente           |
| `idCar`      | `number`  | **Obrigatório**. ID do carro             |
| `isActive`   | `boolean` | **Obrigatório**. Flag de orçamento ativo |

##### Response

```json
{
  "success": true,
  "data": {
    "idInsurance": 1
  },
  "message": null
}
```

---

#### Atualização de Orçamento

```
  PUT /seguradora-audsat/insurance/budget
```

##### Request

```json
{
  "idCustomer": 1,
  "idCar": 1,
  "isActive": true
}
```

| Parâmetro    | Tipo      | Descrição               |
|:-------------|:----------|:------------------------|
| `idCustomer` | `number`  | ID do cliente           |
| `idCar`      | `number`  | ID do carro             |
| `isActive`   | `boolean` | Flag de orçamento ativo |

##### Response

```json
{
  "success": true,
  "data": {
    "idInsurance": 1
  },
  "message": null
}
```

---

#### Consulta de Orçamento

```
  GET /seguradora-audsat/insurance/budget/{insuranceId}
```

##### Request

| Parâmetro     | Tipo     | Descrição       |
|:--------------|:---------|:----------------|
| `insuranceId` | `number` | ID do orçamento |

##### Response

```json
{
  "success": true,
  "data": {
    "idInsurance": 1,
    "car": {
      "idCar": 1,
      "model": "UNO",
      "manufacturer": "Fiat",
      "year": "2010",
      "fipe": 10000.0
    },
    "customer": {
      "idCustomer": 1,
      "name": "Paulo Marcos de Andrade",
      "document": "61387837010",
      "birthdate": "01/04/1999"
    },
    "budget": 800.0,
    "active": true
  },
  "message": null
}
```

