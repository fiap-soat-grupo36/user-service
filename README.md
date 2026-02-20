# User Service

Arquitetura de microserviços para sistema de gestão de oficina mecânica.


## 📋 Arquitetura

Serviço para criação de usuário, geração e validação de tokens JWT, e controle de acesso.

---

## 🏠 Desenvolvimento Local - Guia Completo

Este guia descreve **as duas formas principais de rodar o projeto localmente** para desenvolvimento.

### 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- ✅ **Java 21** - [Download](https://adoptium.net/)
- ✅ **Maven 3.9+** - [Download](https://maven.apache.org/download.cgi)
- ✅ **Docker & Docker Compose** - [Download](https://www.docker.com/products/docker-desktop)
- ✅ **Git** - Para clonar o repositório

**Verificar instalação:**
```bash
java -version    # Deve mostrar Java 21
mvn -version     # Deve mostrar Maven 3.9+
docker --version # Deve mostrar Docker 20.10+
docker compose version
```
---

### 🎯 Opção 1: Docker Compose (⭐ Recomendado)

**Vantagens:** Rápido, isolado, não precisa configurar banco manualmente, simula ambiente de produção.

#### Passo a Passo

**1. Clonar o repositório**
```bash
git clone https://github.com/seu-usuario/user-service.git
cd user-service
```

**2. Subir todos os serviços**
```bash
# Sobe banco de dados + serviços da aplicação
docker compose up -d

# Acompanhar os logs (Ctrl+C para sair)
docker compose logs -f
```

**3. Verificar se tudo está funcionando**

```bash
# Ver status de todos os containers
docker compose ps
```

**4. Acessar os serviços**

📖 **Swagger:**
- URL: http://localhost:8080/swagger-ui.html


**5. Parar tudo quando terminar**

```bash
# Parar mas manter os dados
docker compose stop

# Parar e remover containers (mantém volumes/dados)
docker compose down

# Parar e LIMPAR TUDO (incluindo banco de dados)
docker compose down -v
```

#### 🔧 Comandos Úteis - Docker Compose

```bash
# Ver logs de um serviço específico
docker compose logs -f customer-service

# Reiniciar um serviço específico
docker compose restart auth-service

# Rebuild após mudanças no código
docker compose up -d --build

# Ver uso de recursos
docker stats

# Acessar terminal de um container
docker exec -it customer-service bash
```
---

### 🎯 Opção 2: Maven Local (Sem Containers)

**Vantagens:** Útil para debug, desenvolvimento isolado de um serviço, não precisa de Docker.

**⚠️ Atenção:** Você precisará de um PostgreSQL rodando (pode usar Docker apenas para o banco).

#### Passo a Passo

**1. Subir PostgreSQL (via Docker)**
```bash
docker run -d \
  --name postgres-oficina \
  -e POSTGRES_DB=oficina-db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres:15-alpine

# Verificar se está rodando
docker ps | grep postgres-oficina
```

**2. Build do projeto**
```bash
cd user-service

# Compilar todos os módulos (necessário por causa da shared-library)
mvn clean install -DskipTests
```

**3. Iniciar serviço**

```bash
mvn spring-boot:run
```

**4. Parar tudo**

```bash
# Parar terminal com Ctrl+C

# Parar PostgreSQL
docker stop postgres-oficina
docker rm postgres-oficina
```
---

## ✅ Checklist de Verificação

Após subir o ambiente (qualquer opção), verifique:

- [ ] ✅ **Swagger** (http://localhost:8080/swagger-ui.html) abre corretamente

- [ ] ✅ **PostgreSQL** está acessível (porta 5432)
- [ ] ✅ **Sem erros** nos logs dos serviços

---
## 🎓 Próximos Passos Após Rodar Local

1. 📖 **Explore a API** via Swagger: http://localhost:8080/swagger-ui.html
2. 🧪 **Rode os testes**: `mvn test`

---

## 🔧 Tecnologias

- **Spring Boot 3.5.3** - Framework principal
- **PostgreSQL** - Banco de dados
- **Java 21** - Linguagem de programação
- **Maven** - Gerenciamento de dependências
- **Docker** - Containerização
